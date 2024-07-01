package top.easylove.controller;

import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.easylove.pojo.Role;
import top.easylove.service.IRoleService;
import top.easylove.util.ResultResponse;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResultResponse<String> addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }


    @GetMapping("/test")
//    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public String get(){
        return "123";
    }
}
