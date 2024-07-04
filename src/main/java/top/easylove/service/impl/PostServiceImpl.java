package top.easylove.service.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.dto.PostDto;
import top.easylove.repository.PostRepository;
import top.easylove.service.IPostService;
import top.easylove.util.ResultResponse;

@Service
public class PostServiceImpl implements IPostService {
    @Resource
    private PostRepository postRepository;

    @Override
    public ResultResponse<String> addPost(PostDto postDto) {
        try {
            // Validate PostDto fields
            if (StrUtil.hasEmpty(postDto.getTitle(),postDto.getSummary(),postDto.getContent())){
                return ResultResponse.error(ResultEnum.ERROR);
            }
//            // Create a new Post entity
//            Post post = new Post();
//            post.setTitle(postDto.getTitle());
//            post.setContent(postDto.getContent());
//            post.setSummary(postDto.getSummary());
//            post.setAvatar(postDto.getAvatar());
//            post.setCreatedAt(new Date()); // Set current timestamp as creation time
//            post.setStatus(1); // Assuming default status for new posts is 1 (draft)

//            // Save the post entity to the database
//            postRepository.save(post);

            return ResultResponse.success("Post added successfully.");
        } catch (Exception e) {
            // Handle any unexpected exception
            return ResultResponse.error("Failed to add post: " + e.getMessage());
        }
    }
}
