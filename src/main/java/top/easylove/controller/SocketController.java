package top.easylove.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import top.easylove.common.WebSocket;
import top.easylove.constant.RabbitMQConstants;
import top.easylove.pojo.MessageType;
import top.easylove.pojo.User;
import top.easylove.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/socket")
@RestController
@Slf4j
public class SocketController {

    @Resource
    private WebSocket webSocket;

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private UserRepository userRepository;

    @PostMapping("/userRegisterInformation")
    public List<MessageType> getUserRegisterInformation(@RequestBody MessageType messageType) {
        log.info(String.valueOf(messageType));
        if (StrUtil.isAllNotEmpty(messageType.getFrom())) {
            Optional<User> byId = userRepository.findById("00e43722-900a-405e-91d8-facda7d66da7");
            rabbitTemplate.convertAndSend(RabbitMQConstants.USER_REGISTRATION_QUEUE, byId.get());

//                webSocket.sendMessageByUserId(messageType.getFrom(), messageType.getContent());
        }
        ArrayList<MessageType> messageTypes = new ArrayList<>();
        messageTypes.add(new MessageType("1","1","1","1","1"));
        messageTypes.add(new MessageType("1","1","1","1","1"));
        messageTypes.add(new MessageType("1","1","1","1","1"));
        return messageTypes;
    }
}
