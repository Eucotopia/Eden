package top.easylove.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.easylove.config.WebSocketConfig;
import top.easylove.constant.RedisConstants;
import top.easylove.pojo.User;
import top.easylove.pojo.dto.UserDto;
import top.easylove.pojo.socket.WebSocketMessage;
import top.easylove.util.RedisUtil;
import top.easylove.util.WebSocketUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ServerEndpoint(value = "/websocket/{uid}")
@Component
@Slf4j
public class WebSocket {

    private Session session;
    private String uid;

    @Resource
    private WebSocketUtil webSocketUtil;

    @Resource
    private RedisUtil redisUtil;


    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {

        this.session = session;
        this.uid = uid;

        if (webSocketUtil == null) {
            webSocketUtil = WebSocketConfig.getBean(WebSocketUtil.class);
        }

        if (redisUtil == null) {
            redisUtil = WebSocketConfig.getBean(RedisUtil.class);
        }

        webSocketUtil.addWebSocket(uid, this);
        String key = RedisConstants.PENDING_USER_KEY_PREFIX;
        List<UserDto> userDtoList = redisUtil.getList(key, UserDto.class);

        WebSocketMessage<List<UserDto>> objectWebSocketMessage = new WebSocketMessage<>("adminappro", "system", Collections.singletonList(uid), userDtoList);

        webSocketUtil.sendToUser(objectWebSocketMessage);

    }

    @OnClose
    public void onClose() {
        webSocketUtil.removeWebSocket(uid);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(WebSocketMessage<?> messages) throws IOException {

        CompletableFuture.runAsync(() -> {
            try {
                String jsonMessage = JSONUtil.toJsonStr(messages);
                this.session.getAsyncRemote().sendText(jsonMessage);
                log.info("Message sent asynchronously");
            } catch (Exception e) {
                log.error("Failed to send message asynchronously", e);
            }
        });
    }

}
