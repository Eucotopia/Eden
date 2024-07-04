package top.easylove.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Service;
import top.easylove.pojo.dto.PostDto;
import top.easylove.util.ResultResponse;

@Service
public interface IPostService {

    @Operation(summary = "Add a new post", description = "Adds a new post to the blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResultResponse<String> addPost(PostDto postDto);
}
