package com.pvt.blog.controller;

import java.util.List;

import com.pvt.blog.pojo.dto.UserDTO;
import com.pvt.blog.pojo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.pvt.blog.service.IUserService;
import com.pvt.blog.util.ResultResponse;

import jakarta.annotation.Resource;

/**
 * @author LW
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private IUserService userService;

    // 用户登录
    @PostMapping("/login")
    public ResultResponse<UserVO> userLogin(@RequestBody UserDTO userdto) {
        return userService.userLogin(userdto);
    }

//    // 用户注册
//    @PostMapping
//    public ResultResponse<String> userRegister(@RequestBody User1 user) {
//        return userService.userRegister(user);
//    }

//    // 获取所有用户
//    @GetMapping
//    public ResultResponse<List<User1>> getAllUser() {
//        return userService.getAllUser();
//    }
}
