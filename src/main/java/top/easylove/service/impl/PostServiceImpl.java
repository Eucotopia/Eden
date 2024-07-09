package top.easylove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.easylove.constant.RedisConstants;
import top.easylove.constant.ResultConstants;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Category;
import top.easylove.pojo.Post;
import top.easylove.pojo.Tag;
import top.easylove.pojo.dto.PostDto;
import top.easylove.repository.CategoryRepository;
import top.easylove.repository.PostRepository;
import top.easylove.repository.TagRepository;
import top.easylove.service.IPostService;
import top.easylove.util.RedisUtil;
import top.easylove.util.ResultResponse;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private PostRepository postRepository;

    @Resource
    private TagRepository tagRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ResultResponse<String> addPost(PostDto postDto) {

        if (StrUtil.hasBlank(postDto.getTitle(), postDto.getSummary(), postDto.getContent())) {
            return ResultResponse.error(ResultEnum.ERROR);
        }

        Post post = BeanUtil.copyProperties(postDto, Post.class);

        Set<Tag> finalTags = post.getTags().stream()
                .map(tag -> tagRepository.findByName(tag.getName())
                        .orElseGet(() -> createNewTag(tag)))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        post.setTags(finalTags);

        Set<Category> finalCategories = post.getCategories().stream()
                .map(category -> categoryRepository.findCategoryByName(category.getName()).orElseGet(() -> createNewCategory(category)))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        post.setCategories(finalCategories);

        postRepository.saveAndFlush(post);

        return ResultResponse.success(ResultEnum.POST_ADD_SUCCESS, ResultConstants.POST_ADD_SUCCESS);

    }

    @Override
    @Transactional
    @Operation(summary = "Get a post by ID", description = "Retrieves a post by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post found",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "4011", description = "Invalid ID supplied",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "4010", description = "Post not found",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    })
    public ResultResponse<Post> getPost(
            @Parameter(description = "ID of the post to retrieve", required = true)
            String id) {

        log.info("Attempting to retrieve post with ID: {}", id);

        if (!StringUtils.hasText(id)) {
            log.warn("Invalid post ID provided: {}", id);
            return ResultResponse.error(ResultEnum.INVALID_INPUT);
        }

        try {
            return postRepository.findById(id)
                    .map(post -> {
                        log.info("Post found with ID: {}", id);

                        String key = RedisConstants.POST_VIEW_KEY_PREFIX + id;

                        //TODO 防止重复刷浏览量，应该通过 ip 进行防重
                        redisUtil.increment(key);

                        return ResultResponse.success(ResultEnum.SUCCESS, post);
                    })
                    .orElseGet(() -> {
                        log.warn("Post not found with ID: {}", id);
                        return ResultResponse.error(ResultEnum.POST_NOT_FOUND);
                    });
        } catch (Exception e) {
            log.error("Error occurred while retrieving post with ID: {}", id, e);
            return ResultResponse.error(ResultEnum.INTERNAL_SERVER_ERROR);
        }
    }

    private Tag createNewTag(Tag tag) {

        if (StrUtil.hasBlank(tag.getName(), tag.getDescription(), tag.getColor(), tag.getIcon())) {
            log.warn("Incomplete tag data: {}", tag);
            return null;
        }

        try {
            log.info("Creating new tag: {}", tag);
            return tagRepository.save(tag);
        } catch (Exception e) {
            log.error("Error creating new tag: ", e);
            return null;
        }
    }

    private Category createNewCategory(Category category) {

        if (StrUtil.hasBlank(category.getName(), category.getDescription())) {
            log.warn("Incomplete tag data: {}", category);
            return null;
        }

        try {
            log.info("Creating new category: {}", category);
            return categoryRepository.save(category);
        } catch (Exception e) {
            log.error("Error creating new tag: ", e);
            return null;
        }
    }
}


