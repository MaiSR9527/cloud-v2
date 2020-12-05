package consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 15:29
 * @version: v1.0
 */
@Component
@EnableBinding(Sink.class)
public class Consumer {
    @StreamListener(Sink.INPUT)
    public void receiver(Message<String> message ) throws Exception {
        Channel channel = (com.rabbitmq.client.Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("Input Stream 2 接受数据：" + message.getPayload());
        System.out.println("消费完毕------------ channel:"+channel+"  deliveryTag:"+deliveryTag);

    }
}
