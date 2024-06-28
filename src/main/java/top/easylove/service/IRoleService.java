package top.easylove.service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import top.easylove.pojo.Role;
import top.easylove.util.ResultResponse;

@Service
public interface IRoleService {

    @Operation(summary = "Add Role", description = "Add a new role to the system")
    ResultResponse<String> addRole(Role role);

}
