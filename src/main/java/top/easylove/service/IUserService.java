package top.easylove.service;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.AuthenticationDto;
import top.easylove.pojo.dto.UserDto;
import top.easylove.pojo.vo.AuthenticationVO;
import top.easylove.util.ResultResponse;

@Service
public interface IUserService {

    @Operation(summary = "Authenticate User", description = "Authenticate user login")
    ResultResponse<AuthenticationVO> authenticateUser(AuthenticationDto authenticationDto);

    @Operation(summary = "Register User", description = "Register User")
    ResultResponse<String> registerUser(AuthenticationDto userRegistrationDto);

    ResultResponse<String> getVerificationCodeByEmail(String email) ;

    ResultResponse<Boolean> verifyCode(UserDto userDto);

    ResultResponse<String> resetPassword(UserDto userDto);
}
