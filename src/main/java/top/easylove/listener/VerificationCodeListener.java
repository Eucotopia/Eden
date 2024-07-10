package top.easylove.listener;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import top.easylove.constant.RabbitMQConstants;
import top.easylove.constant.RedisConstants;
import top.easylove.util.EmailUtil;

import java.util.Map;

@Component
public class VerificationCodeListener {

    @Resource
    private EmailUtil emailUtil;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstants.EMAIL_VERIFY_CODE_QUEUE, durable = "true"),
            exchange = @Exchange(value = RabbitMQConstants.EMAIL_VERIFY_CODE_EXCHANGE),
            key = RabbitMQConstants.EMAIL_VERIFY_CODE_ROUTING_KEY))
    public void messageHandler(@Payload Map<String, String> stringMap) {
        String verifyCode = stringMap.get("verifyCode");
        String email = stringMap.get("email");
        System.out.println("email:" + email);
        emailUtil.sendSimpleMessage(email, "Verification Code", "Your verification code is: " + verifyCode);
    }
}