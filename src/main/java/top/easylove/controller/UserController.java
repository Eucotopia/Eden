package top.easylove.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.UserDto;
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
    @PostMapping("/login")
    public ResultResponse<User> authenticateUser(@RequestBody UserDto userDto) {
        return userService.authenticateUser(userDto);
    }
}
