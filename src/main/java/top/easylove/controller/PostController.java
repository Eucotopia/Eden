package top.easylove.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.easylove.pojo.Post;
import top.easylove.pojo.dto.PostDto;
import top.easylove.service.IPostService;
import top.easylove.util.ResultResponse;

@RestController
@RequestMapping("/post")
@Tag(name = "Post Controller", description = "API endpoints for managing blog posts")
public class PostController {

    @Resource
    private IPostService postService;

    @PostMapping
    @Operation(summary = "Add a new post", description = "Creates a new blog post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post created successfully",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    })
    public ResultResponse<String> addPost(
            @Parameter(description = "Post data transfer object", required = true)
            @RequestBody PostDto postDto) {
        return postService.addPost(postDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a post by ID", description = "Retrieves a blog post by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    })
    public ResultResponse<Post> getPost(
            @Parameter(description = "ID of the post to retrieve", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        return postService.getPost(id);
    }
}