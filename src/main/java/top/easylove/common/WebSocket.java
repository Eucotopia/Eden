package top.easylove.common;

import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.easylove.config.WebSocketConfig;

import java.io.IOException;

/**
 * 这个 socket 只是在做推送，例如注册用户需要管理员审批等
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
@Slf4j
public class WebSocket {

    @Resource
    private WebSocketManager webSocketManager;

    private Session session;
    private String userId;


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        log.info("socket connected : {}", userId);
        if (webSocketManager == null) {
            webSocketManager = WebSocketConfig.getBean(WebSocketManager.class);
        }
        webSocketManager.addWebSocket(userId, this);
        try {
            sendMessage(String.valueOf(this.session.getQueryString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        webSocketManager.removeWebSocket(userId);
    }

//    @OnMessage
//    public void onMessage(String message, Session session) {
//        for (String s : webSocketConcurrentReferenceHashMap.keySet()) {
//            webSocketConcurrentReferenceHashMap.get(s);
//        }
//    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        log.info("发送消息" + message);
    }

    public void sendMessageByUserId(String userId, String message) throws IOException {
        log.info("接受的用户:{}",userId);
        webSocketManager.sendMessageByUserId(userId, message);
    }

//    public void sendInfo(String message) throws IOException {
//        for (String s : webSocketConcurrentReferenceHashMap.keySet()) {
//            webSocketConcurrentReferenceHashMap.get(s).sendMessage(message);
//        }
//    }
}
