package top.easylove.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageType {
    // 消息类型
    private String type;
    // 消息内容
    private String content;
    // 发送者
    private String from;
    // 接收者
    private String to;
    // 频道
    private String channel;
}
