package top.easylove.common;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.easylove.config.WebSocketConfig;
import top.easylove.pojo.MessageType;
import top.easylove.util.WebSocketUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ServerEndpoint(value = "/websocket/{uid}")
@Component
@Slf4j
public class WebSocket {

    @Resource
    private WebSocketUtil webSocketUtil;

    private Session session;
    private String uid;


    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        this.session = session;
        this.uid = uid;
        log.info("socket connected : {}", uid);
        if (webSocketUtil == null) {
            webSocketUtil = WebSocketConfig.getBean(WebSocketUtil.class);
        }
        webSocketUtil.addWebSocket(uid, this);
        try {
            sendMessage(String.valueOf(this.session.getQueryString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        webSocketUtil.removeWebSocket(uid);
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
        log.info("发送消息{}", message);
    }

    public void sendMessage(String uid, String message) throws IOException {
        log.info("接受的用户:{}", uid);
    }

    public <T> void sendMessage(List<T> values) throws IOException {
        log.info("接受的用户:{}", values);
        this.session.getBasicRemote().sendText(JSONUtil.toJsonStr(values));
    }

}
