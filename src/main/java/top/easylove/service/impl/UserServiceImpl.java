package top.easylove.service.impl;

import cn.hutool.core.lang.Validator;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.UserDto;
import top.easylove.repository.UserRepository;
import top.easylove.service.IUserService;
import top.easylove.util.ResultResponse;
import top.easylove.enums.ResultEnum;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    public ResultResponse<User> authenticateUser(UserDto userDto) {

        if (!Validator.isEmail(userDto.getEmail())) {
            return ResultResponse.error(ResultEnum.INVALID_EMAIL_FORMAT);
        }

        User user = userRepository.findUserByEmail(userDto.getEmail()).orElseThrow(() -> new RuntimeException(ResultEnum.USER_NOT_FOUND.getMessage()));

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);



        return null;
    }
}
