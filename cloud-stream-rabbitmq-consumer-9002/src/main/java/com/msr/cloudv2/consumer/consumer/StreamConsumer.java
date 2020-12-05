package com.msr.cloudv2.consumer.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 14:08
 * @version: v1.0
 */
@Component
@EnableBinding(Sink.class)
public class StreamConsumer {


    @StreamListener(Sink.INPUT)
    public void receiver(@Payload String uuid,
                         @Header(AmqpHeaders.CHANNEL) Channel channel,
                         @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws Exception {
        System.out.println("Input Stream 2 接受数据：" + uuid);
        System.out.println("消费完毕------------ channel:" + "  deliveryTag:" + deliveryTag + " channel:" + channel);
        channel.basicAck(deliveryTag, false);
    }
}
