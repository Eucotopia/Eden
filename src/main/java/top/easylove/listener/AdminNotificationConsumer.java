package top.easylove.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.expression.ExpressionException;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.easylove.common.WebSocket;
import top.easylove.constant.RabbitMQConstants;
import top.easylove.constant.RedisConstants;
import top.easylove.constant.ResultConstants;
import top.easylove.constant.RoleConstants;
import top.easylove.pojo.Role;
import top.easylove.pojo.User;
import top.easylove.repository.RoleRepository;
import top.easylove.repository.UserRepository;
import top.easylove.util.RedisUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class AdminNotificationConsumer {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private WebSocket webSocket;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstants.USER_REGISTRATION_QUEUE, durable = "true"),
            exchange = @Exchange(value = RabbitMQConstants.USER_REGISTRATION_EXCHANGE),
            key = RabbitMQConstants.USER_REGISTRATION_ROUTING_KEY))
    public void handleNewUserRegistration(@Payload User newUser) {
        if (newUser == null || StrUtil.isBlank(newUser.getId())) {
            log.error("Received invalid user data");
            return;
        }

        String key = RedisConstants.PENDING_USER_KEY_PREFIX + newUser.getId();
        // 设置 redis 内容
        // 当由用户注册时，主动给在线的管理员发送信息
        // 如果管理员审核了该用户，则删除 redis 信息
        redisUtil.set(key, newUser);
        if (redisUtil.set(key, newUser)) {
            log.info("New user registration stored in Redis: {}", newUser.getId());
            notifyAdmins(newUser);
        } else {
            log.error("Failed to store new user registration in Redis: {}", newUser.getId());
        }

    }

    private void notifyAdmins(User newUser) {
        Set<Role> roles = roleRepository.findRolesByName(RoleConstants.ADMIN).orElse(null);
        log.info("roles" + roles);
        assert roles != null;
        if (roles.isEmpty()) {
            return;
        }
        List<User> users = userRepository.findUsersByRolesIn(roles).orElseThrow(() -> new UsernameNotFoundException(ResultConstants.USER_NOT_FOUND));
        log.info("users:" + users);
        // 传递当前注册用户的信息
        for (User user : users) {
            try {
                String jsonStr = JSONUtil.toJsonStr(newUser);
                webSocket.sendMessageByUserId(user.getId(), jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}