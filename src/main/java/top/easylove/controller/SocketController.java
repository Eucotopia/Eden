package top.easylove.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.easylove.common.WebSocket;
import top.easylove.constant.RabbitMQConstants;
import top.easylove.pojo.MessageType;
import top.easylove.pojo.User;
import top.easylove.repository.UserRepository;
import top.easylove.service.ISocketService;
import top.easylove.util.ResultResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/socket")
@RestController
@Slf4j
public class SocketController {
    @Resource
    private ISocketService socketService;

    /**
     * 当管理员进行登录时，返回给管理员待审核的注册用户
     */
    @PostMapping("/getUnapprovedUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResultResponse<List<MessageType>> getUnapprovedUsers() {
        return socketService.getUnapprovedUsers();
    }
}
