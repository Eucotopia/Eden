package top.easylove.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.AuthenticationDto;
import top.easylove.pojo.vo.AuthenticationVO;
import top.easylove.service.IUserService;
import top.easylove.util.ResultResponse;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "User Controller", description = "Operations related to user management")
public class UserController {

    @Resource
    private IUserService userService;

    @Operation(summary = "Authenticate User", description = "Authenticate user login")
    @Parameters({@Parameter(name = "userDto", description = "userDto")})
    @ApiResponses({
            @ApiResponse(responseCode = "2002", description = "请求成功"),
            @ApiResponse(responseCode = "4001", description = "邮箱格式不正确"),
    })
    @PostMapping("/login")
    public ResultResponse<AuthenticationVO> authenticateUser(@RequestBody AuthenticationDto authenticationDto) {
        return userService.authenticateUser(authenticationDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "2001", description = "请求成功"),
            @ApiResponse(responseCode = "4001", description = "邮箱格式不正确"),
            @ApiResponse(responseCode = "4002", description = "用户存在"),
    })
    @Parameters({@Parameter(name = "AuthenticationDto", description = "UserRegistrationDto")})
    @Operation(summary = "Register User", description = "Register User")
    @PostMapping
    public ResultResponse<String> registerUser(@RequestBody AuthenticationDto authenticationDto) {
        return userService.registerUser(authenticationDto);
    }

    @PostMapping("/verification-code/{email}")
    public ResultResponse<String> getVerificationCode(@PathVariable String email) {
        return userService.getVerificationCodeByEmail(email);
    }
}
