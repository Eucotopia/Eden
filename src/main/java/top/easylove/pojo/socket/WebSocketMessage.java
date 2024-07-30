package top.easylove.pojo.socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage<T> {
    private String type;
    private String sender;
    private List<String> recipients;
    private T content;
}
