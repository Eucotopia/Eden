package top.easylove.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.easylove.common.WebSocket;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.AuthenticationDto;
import top.easylove.pojo.dto.UserDto;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                    content = @Content(schema = @Schema(implementation = AuthenticationVO.class))),
            @ApiResponse(responseCode = "2002", description = "Successfully authenticated"),
            @ApiResponse(responseCode = "4001", description = "Invalid email format"),
            @ApiResponse(responseCode = "401", description = "Authentication failed")
    })
    @PostMapping("/login")
    public ResultResponse<AuthenticationVO> authenticateUser(
            @Parameter(description = "Authentication details", required = true)
            @RequestBody AuthenticationDto authenticationDto) {
        return userService.authenticateUser(authenticationDto);
    }

    @Operation(summary = "Register User", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "2001", description = "User successfully registered"),
            @ApiResponse(responseCode = "4001", description = "Invalid email format"),
            @ApiResponse(responseCode = "4002", description = "User already exists")
    })
    @PostMapping
    public ResultResponse<String> registerUser(
            @Parameter(description = "User registration details", required = true)
            @RequestBody AuthenticationDto authenticationDto) {
        return userService.registerUser(authenticationDto);
    }

    @Operation(summary = "Get Verification Code", description = "Send verification code to user's email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verification code sent successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "4001", description = "Invalid email format"),
            @ApiResponse(responseCode = "4004", description = "User not found")
    })
    @PostMapping("/getVerifyCode/{email}")
    public ResultResponse<String> getVerificationCode(
            @Parameter(description = "User's email address", required = true)
            @PathVariable String email) {
        return userService.getVerificationCodeByEmail(email);
    }

    @Operation(summary = "Verify Code", description = "Verify the code sent to user's email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Code verified successfully",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "4001", description = "Invalid email format"),
            @ApiResponse(responseCode = "4003", description = "Verification code expired"),
            @ApiResponse(responseCode = "4005", description = "Invalid verification code")
    })
    @PostMapping("/verifyCode")
    public ResultResponse<Boolean> verifyCode(
            @Parameter(description = "User details with verification code", required = true)
            @RequestBody UserDto userDto) {
        return userService.verifyCode(userDto);
    }

    @Operation(summary = "Reset Password", description = "Reset user's password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "4001", description = "Invalid email format"),
            @ApiResponse(responseCode = "4003", description = "Verification code expired"),
            @ApiResponse(responseCode = "4004", description = "User not found"),
            @ApiResponse(responseCode = "4005", description = "Invalid verification code")
    })
    @PostMapping("/resetPassword")
    public ResultResponse<String> resetPassword(
            @Parameter(description = "User details for password reset", required = true)
            @RequestBody UserDto userDto) {
        return userService.resetPassword(userDto);
    }

    @GetMapping("/{uid}")
    public ResultResponse<User> getUser(@PathVariable String uid) {
        return userService.getUser(uid);
    }

}