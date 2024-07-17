package top.easylove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.easylove.constant.RabbitMQConstants;
import top.easylove.constant.RedisConstants;
import top.easylove.constant.RoleConstants;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Role;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.AuthenticationDto;
import top.easylove.pojo.dto.UserDto;
import top.easylove.pojo.vo.AuthenticationVO;
import top.easylove.repository.RoleRepository;
import top.easylove.repository.UserRepository;
import top.easylove.service.IUserService;
import top.easylove.util.EmailUtil;
import top.easylove.util.JwtTokenProvider;
import top.easylove.util.RedisUtil;
import top.easylove.util.ResultResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private EmailUtil emailUtil;

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

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String authorization = jwtTokenProvider.generateToken(authentication);

        return ResultResponse.success(ResultEnum.USER_LOGIN_SUCCESS, new AuthenticationVO(user.getId(), user.getUsername(), user.getEmail(), authorization, user.getAvatar()));
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

        if (StrUtil.isBlank(user.getUsername())) {
            user.setUsername(user.getEmail().split("@")[0]);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> role = roleRepository.findRoleByName(RoleConstants.GUEST);

        if (role.isPresent()) {
            user.setRoles(Collections.singleton(role.orElse(null)));
        }

        userRepository.save(user);

        rabbitTemplate.convertAndSend(RabbitMQConstants.USER_REGISTRATION_QUEUE, user);

        return ResultResponse.success(ResultEnum.USER_REGISTER_SUCCESS, null);
    }

    @Override
    public ResultResponse<String> getVerificationCodeByEmail(String email) {

        if (!Validator.isEmail(email)) {
            return ResultResponse.error(ResultEnum.INVALID_EMAIL_FORMAT);
        }

        if (!userRepository.existsUserByEmail(email)) {
            return ResultResponse.error(ResultEnum.USER_NOT_FOUND);
        }

        generateAndSendVerificationCode(email);

        return ResultResponse.success(ResultEnum.SUCCESS, "Verification code sent successfully");
    }

    @Override
    public ResultResponse<Boolean> verifyCode(UserDto userDto) {
        if (!Validator.isEmail(userDto.getEmail())) {
            return ResultResponse.error(ResultEnum.INVALID_EMAIL_FORMAT);
        }

        String storeVerifyCode = redisUtil.get(RedisConstants.VERIFY_CODE_KEY_PREFIX + userDto.getEmail(), String.class).orElse(null);

        if (storeVerifyCode == null) {
            return ResultResponse.error(ResultEnum.VERIFY_CODE_KEY_EXPIRED);
        }

        if (storeVerifyCode.equals(userDto.getVerifyCode())) {

            return ResultResponse.success(ResultEnum.SUCCESS, Boolean.TRUE);
        }

        return ResultResponse.error(ResultEnum.INVALID_VERIFICATION_CODE);
    }

    @Override
    @Transactional
    public ResultResponse<String> resetPassword(UserDto userDto) {

        String storeVerifyCode = redisUtil.get(RedisConstants.VERIFY_CODE_KEY_PREFIX + userDto.getEmail(), String.class).orElse(null);

        if (!Validator.isEmail(userDto.getEmail())) {
            return ResultResponse.error(ResultEnum.INVALID_EMAIL_FORMAT);
        }

        if (storeVerifyCode == null) {
            return ResultResponse.error(ResultEnum.VERIFY_CODE_KEY_EXPIRED);
        }

        if (storeVerifyCode.equals(userDto.getVerifyCode())) {

            redisUtil.delete(RedisConstants.VERIFY_CODE_KEY_PREFIX + userDto.getEmail());
            // 执行修改密码
            User user = userRepository.findUserByEmail(userDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException(ResultEnum.USER_NOT_FOUND.getMessage()));

            user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));

            userRepository.save(user);

            return ResultResponse.success(ResultEnum.SUCCESS, "修改密码成功");
        }
        return ResultResponse.error(ResultEnum.ERROR);
    }

    @Async
    protected void generateAndSendVerificationCode(String email) {

        // 随机生成验证码
        String verifyCode = RandomUtil.randomNumbers(6);

        // 设置 redis key
        redisUtil.set(RedisConstants.VERIFY_CODE_KEY_PREFIX + email, verifyCode, 10, TimeUnit.MINUTES);


        Map<String, String> message = new HashMap<>();
        message.put("email", email);
        message.put("verifyCode", verifyCode);

        rabbitTemplate.convertAndSend(RabbitMQConstants.EMAIL_VERIFY_CODE_EXCHANGE, RabbitMQConstants.EMAIL_VERIFY_CODE_ROUTING_KEY, message);

        emailUtil.sendSimpleMessage(email, "Verification Code", "Your verification code is: " + verifyCode);

    }
}
