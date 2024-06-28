package top.easylove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.easylove.common.RoleConstants;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Role;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.AuthenticationDto;
import top.easylove.pojo.vo.AuthenticationVO;
import top.easylove.repository.RoleRepository;
import top.easylove.repository.UserRepository;
import top.easylove.service.IUserService;
import top.easylove.util.JwtTokenProvider;
import top.easylove.util.ResultResponse;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResultResponse<AuthenticationVO> authenticateUser(AuthenticationDto authenticationDto) {

        if (!Validator.isEmail(authenticationDto.getEmail())) {
            return ResultResponse.error(ResultEnum.INVALID_EMAIL_FORMAT);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword()));

        User user = userRepository.findUserByEmail(authenticationDto.getEmail()).orElseThrow(() -> new RuntimeException(ResultEnum.USER_NOT_FOUND.getMessage()));

        if (user.getStatus() == 2) {
            return ResultResponse.error(ResultEnum.USER_PENDING_APPROVAL);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String authorization = jwtTokenProvider.generateToken(authentication);

        return ResultResponse.success(ResultEnum.USER_LOGIN_SUCCESS, new AuthenticationVO(user.getUsername(), user.getEmail(), authorization, user.getAvatar()));

    }

    @Override
    public ResultResponse<String> registerUser(AuthenticationDto authenticationDto) {

        if (!Validator.isEmail(authenticationDto.getEmail())) {
            return ResultResponse.error(ResultEnum.INVALID_EMAIL_FORMAT);
        }

        if (userRepository.existsUserByEmail(authenticationDto.getEmail())) {
            return ResultResponse.error(ResultEnum.USER_EXIST);
        }

        User user = BeanUtil.copyProperties(authenticationDto, User.class);

        user.setUsername(user.getEmail().split("@")[0]);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        Optional<Role> role = roleRepository.findRoleByName(RoleConstants.GUEST);

        if (role.isPresent()) {
            user.setRoles(Collections.singleton(role.orElse(null)));
        }

        userRepository.save(user);

        return ResultResponse.success(ResultEnum.USER_REGISTER_SUCCESS, null);
    }
}
