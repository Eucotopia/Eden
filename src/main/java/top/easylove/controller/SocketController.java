package top.easylove.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easylove.pojo.MessageType;
import top.easylove.service.ISocketService;
import top.easylove.util.ResultResponse;

import java.util.List;

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
