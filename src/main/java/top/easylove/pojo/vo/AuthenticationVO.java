package top.easylove.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Schema(description = "Authentication VO")
public class AuthenticationVO {

    @Schema(description = "Username of the user", example = "john.doe")
    private String username;

    @Schema(description = "Email of the user", example = "john.doe@qq.com")
    private String email;

    @Schema(description = "authorization")
    private String authorization;

    @Schema(description = "Avatar of the user", example = "https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
    private String avatar;

}
