package top.easylove.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import top.easylove.common.WebSocket;
import top.easylove.constant.RedisConstants;
import top.easylove.pojo.MessageType;
import top.easylove.pojo.dto.UserDto;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketUtil {

    @Resource
    private RedisUtil redisUtil;

    private final Map<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    public void addWebSocket(String uid, WebSocket webSocket) {
        webSocketMap.put(uid, webSocket);
        log.info("New WebSocket connection added for user: {}", uid);
    }

    public void removeWebSocket(String userId) {
        WebSocket remove = webSocketMap.remove(userId);
        if (remove != null) {
            log.info("WebSocket connection removed for user: {}", userId);
        }
    }

    public void sendMessage(String userId, String message) throws IOException {
        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            log.info("Send message to user: {}", userId);
            webSocketMap.get(userId).sendMessage(message);
        }
    }

    public void getUnapprovedUsers(String uid) {
        String key = RedisConstants.PENDING_USER_KEY_PREFIX;
        List<UserDto> userDtoList = redisUtil.getList(key, UserDto.class);
        log.info("getUnapprovedUsers userDtoSet: {}", userDtoList);
        try {
            if (StrUtil.isNotBlank(uid) && webSocketMap.containsKey(uid)) {
                log.info("afdasfsdfaf");
                List<MessageType> messages = new ArrayList<>();
                for (UserDto userDto : userDtoList) {
                    MessageType message = new MessageType(
                            "ADMIN_NOTIFICATION",
                            JSONUtil.toJsonStr(userDto),  // 将 UserDto 转换为 JSON 字符串
                            "system",
                            uid,
                            "admin_notification"
                    );
                    messages.add(message);
                }
                webSocketMap.get(uid).sendMessage(messages);
            }
        } catch (Exception e) {
            log.error("Error sending unapproved users to user " + uid, e);
        }
    }

    public void broadcastMessage(String message) {
        for (WebSocket webSocket : webSocketMap.values()) {
            try {
                webSocket.sendMessage(message);
            } catch (IOException e) {
                // 处理异常，可能需要移除失效的连接
            }
        }
    }

    public int getOnlineCount() {
        return webSocketMap.size();
    }

    public Map<String, WebSocket> getAllWebSockets() {
        return new ConcurrentHashMap<>(webSocketMap);
    }
}