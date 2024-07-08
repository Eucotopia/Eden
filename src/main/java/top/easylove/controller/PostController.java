package top.easylove.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easylove.pojo.dto.PostDto;
import top.easylove.service.IPostService;
import top.easylove.util.ResultResponse;

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
