package top.easylove.service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.UserDto;
import top.easylove.util.ResultResponse;

@Service
public interface IUserService {
    @Operation(summary = "Authenticate User", description = "Authenticate user login")
    ResultResponse<User> authenticateUser(UserDto userDto);
}
