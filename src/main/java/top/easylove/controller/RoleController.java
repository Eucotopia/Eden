package top.easylove.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easylove.pojo.Role;
import top.easylove.service.IRoleService;
import top.easylove.util.ResultResponse;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @PostMapping
    public ResultResponse<String> addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }
}
