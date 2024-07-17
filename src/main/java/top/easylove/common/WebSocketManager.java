package top.easylove.common;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class WebSocketManager {

    private final ConcurrentReferenceHashMap<String, WebSocket> webSocketMap = new ConcurrentReferenceHashMap<>();

    public void addWebSocket(String userId, WebSocket webSocket) {
        webSocketMap.put(userId, webSocket);
    }

    public void removeWebSocket(String userId) {
        webSocketMap.remove(userId);
    }

    public void sendMessageByUserId(String userId, String message) throws IOException {
        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
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
        return webSocketMap;
    }
}