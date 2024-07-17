package top.easylove.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.expression.ExpressionException;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.easylove.common.WebSocket;
import top.easylove.constant.RabbitMQConstants;
import top.easylove.constant.RedisConstants;
import top.easylove.constant.ResultConstants;
import top.easylove.constant.RoleConstants;
import top.easylove.pojo.CustomUserDetails;
import top.easylove.pojo.Role;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.UserDto;
import top.easylove.repository.RoleRepository;
import top.easylove.repository.UserRepository;
import top.easylove.util.RedisUtil;
import top.easylove.util.SocketUtil;
import top.easylove.util.WebSocketUtil;

import java.util.HashSet;
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
    private WebSocketUtil webSocketUtil;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstants.USER_REGISTRATION_QUEUE, durable = "true"),
            exchange = @Exchange(value = RabbitMQConstants.USER_REGISTRATION_EXCHANGE),
            key = RabbitMQConstants.USER_REGISTRATION_ROUTING_KEY))
    public void handleNewUserRegistration(@Payload UserDto userDto) {

        if (BeanUtil.isNotEmpty(userDto)) {
            String key = RedisConstants.PENDING_USER_KEY_PREFIX;
            redisUtil.appendToSet(key, CollectionUtil.newHashSet(userDto));
        }

        Set<Role> roles = roleRepository.findRolesByName(RoleConstants.ADMIN).orElse(null);

        if (roles == null) {
            return;
        }

        List<User> users = userRepository.findUsersByRolesIn(roles).orElseThrow(() -> new UsernameNotFoundException(ResultConstants.USER_NOT_FOUND));

        for (User user : users) {
            try {
                webSocketUtil.getUnapprovedUsers(user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}