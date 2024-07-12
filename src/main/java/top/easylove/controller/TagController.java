package top.easylove.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.easylove.pojo.Post;
import top.easylove.pojo.Tag;
import top.easylove.service.ITagService;
import top.easylove.util.ResultResponse;

import java.util.List;

@RestController
@RequestMapping("/tag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag Management", description = "API endpoints for managing tags")
public class TagController {

    @Resource
    private ITagService tagService;

    @PostMapping
    @Operation(summary = "Add new tags", description = "Add one or more new tags to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tags added successfully",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResultResponse<String> addTags(
            @Parameter(description = "List of tags to be added", required = true)
            @RequestBody List<Tag> tags) {
        return tagService.addTags(tags);
    }

    @GetMapping("/count")
    @Operation(summary = "Get total tag count", description = "Retrieve the total number of tags in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tag count",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResultResponse<Long> getTagCount() {
        return tagService.getTagCount();
    }

    @GetMapping
    @Operation(summary = "Get all tags", description = "Retrieve a list of all tags in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tags",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResultResponse<List<Tag>> getTags() {
        return tagService.getTags();
    }
}