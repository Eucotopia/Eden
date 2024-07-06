package top.easylove.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Post;
import top.easylove.pojo.dto.PostDto;
import top.easylove.repository.PostRepository;
import top.easylove.service.IPostService;
import top.easylove.util.ResultResponse;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private IPostService postService;

    @PostMapping
    public ResultResponse<String> addPost(@RequestBody PostDto postDto) {
        return postService.addPost(postDto);
    }
}
