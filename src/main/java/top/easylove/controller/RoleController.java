package top.easylove.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Role;
import top.easylove.repository.RoleRepository;
import top.easylove.service.IRoleService;
import top.easylove.util.ResultResponse;

import java.util.List;

@RestController
@RequestMapping("/role")
@Tag(name = "Role Controller", description = "Operations related to role management")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Operation(summary = "Add Role", description = "Add a new role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully added role"),
            @ApiResponse(responseCode = "403", description = "Forbidden to add role")
    })

    @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
    @PostMapping
    public ResultResponse<String> addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

}
