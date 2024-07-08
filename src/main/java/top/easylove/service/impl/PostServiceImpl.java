package top.easylove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.easylove.constant.ResultConstants;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Post;
import top.easylove.pojo.Tag;
import top.easylove.pojo.dto.PostDto;
import top.easylove.repository.PostRepository;
import top.easylove.repository.TagRepository;
import top.easylove.service.IPostService;
import top.easylove.util.ResultResponse;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

    @Resource
    private PostRepository postRepository;

    @Resource
    private TagRepository tagRepository;

    @Override
    public ResultResponse<String> addPost(PostDto postDto) {

        // 判断字段是否为空
        if (StrUtil.hasBlank(postDto.getTitle(), postDto.getSummary(), postDto.getContent())) {
            return ResultResponse.error(ResultEnum.ERROR);
        }

        Post post = BeanUtil.copyProperties(postDto, Post.class);

        Set<Tag> finalTags = new HashSet<>();

        for (Tag tag : post.getTags()) {
            Tag finalTag = tagRepository.findById(tag.getId())
                    .orElseGet(() -> createNewTag(tag));

            if (finalTag != null) {
                finalTags.add(finalTag);
            }
        }

        post.setTags(finalTags);

        postRepository.saveAndFlush(post);

        return ResultResponse.success(ResultEnum.POST_ADD_SUCCESS, ResultConstants.POST_ADD_SUCCESS);
    }

    private Tag createNewTag(Tag tag) {

        // 检查必要字段是否为空
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
}


