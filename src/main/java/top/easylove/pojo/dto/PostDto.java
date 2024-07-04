package top.easylove.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Post DTO")
public class PostDto {

    @Schema(description = "Title of the post")
    private String title;

    @Schema(description = "Content of the post")
    private String content;

    @Schema(description = "Summary of the post")
    private String summary;

    @Schema(description = "URL of the avatar image for the post")
    private String avatar;
}
