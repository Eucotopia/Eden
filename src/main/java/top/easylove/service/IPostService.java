package top.easylove.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import top.easylove.pojo.Post;
import top.easylove.pojo.dto.PostDto;
import top.easylove.util.ResultResponse;

@Service
@Tag(name = "Post Service", description = "API for managing blog posts")
public interface IPostService {

    @Operation(summary = "Add a new post", description = "Adds a new post to the blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post added successfully",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    })
    ResultResponse<String> addPost(
            @Parameter(description = "Post data transfer object", required = true) PostDto postDto);

    @Operation(summary = "Get a post by ID", description = "Retrieves a post by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    })
    ResultResponse<Post> getPost(
            @Parameter(description = "ID of the post to retrieve", required = true) String id);
}