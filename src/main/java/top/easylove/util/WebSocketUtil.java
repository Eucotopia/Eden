package top.easylove.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.easylove.common.WebSocket;
import top.easylove.constant.RedisConstants;
import top.easylove.pojo.MessageType;
import top.easylove.pojo.dto.UserDto;
import top.easylove.pojo.socket.WebSocketMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

//    public void getUnapprovedUsers(String uid) {
//        String key = RedisConstants.PENDING_USER_KEY_PREFIX;
//        List<UserDto> userDtoList = redisUtil.getList(key, UserDto.class);
//        log.info("getUnapprovedUsers userDtoSet: {}", userDtoList);
//        try {
//            if (StrUtil.isNotBlank(uid) && webSocketMap.containsKey(uid)) {
//                List<MessageType> messages = new ArrayList<>();
//                for (UserDto userDto : userDtoList) {
//                    MessageType message = new MessageType(
//                            "ADMIN_NOTIFICATION",
//                            JSONUtil.toJsonStr(userDto),  // 将 UserDto 转换为 JSON 字符串
//                            "system",
//                            uid,
//                            "admin_notification"
//                    );
//                    messages.add(message);
//                }
//                webSocketMap.get(uid).sendMessage(messages);
//            }
//        } catch (Exception e) {
//            log.error("Error sending unapproved users to user " + uid, e);
//        }
//    }

    public void broadcastMessage(WebSocketMessage<?> message) {
        // 广播消息给所有连接的客户端
    }

    public void sendToUser(WebSocketMessage<?> message) {
        if (message == null || CollUtil.isEmpty(message.getRecipients())) {
            log.warn("Invalid message or empty recipients list");
            return;
        }

        String messageJson = JSONUtil.toJsonStr(message);

        message.getRecipients().stream()
                .filter(StrUtil::isNotBlank)
                .filter(webSocketMap::containsKey)
                .forEach(recipient -> {
                    try {
                        webSocketMap.get(recipient).sendMessage(message);
                        log.debug("Message sent to user: {}", recipient);
                    } catch (Exception e) {
                        log.error("Failed to send message to user: {}", recipient, e);
                    }
                });
    }

    public void sendToUsers(WebSocketMessage<?> message) {
        // 发送消息给多个用户

    }

    public int getOnlineCount() {
        return webSocketMap.size();
    }

    public Map<String, WebSocket> getAllWebSockets() {
        return new ConcurrentHashMap<>(webSocketMap);
    }

}