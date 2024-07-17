package top.easylove.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for user authentication")
public class UserDto implements Serializable {
    @Transient
    private static final Long SERIAL_VERSION_UID = -6249791470714667710L;

    @Schema(description = "id of the user", example = "john_doe")
    private String id;

    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @Schema(description = "Password of the user", example = "password123")
    private String password;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "verifyCode for reset password", example = "117155")
    private String verifyCode;

}
