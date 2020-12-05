package com.msr.cloudv2.stream.service.impl;

import com.msr.cloudv2.stream.service.IMessageProviderService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 定义消息的推送管道
 * @author: MaiShuRen
 * @date: 2020/4/21 13:22
 * @version: v1.0
 */
@EnableBinding(Source.class)
public class MessageProviderServiceImpl implements IMessageProviderService {

    @Resource
    private MessageChannel output;

    @Override
    public String send(Object object, Map<String, Object> properties) {

        String serial = UUID.randomUUID().toString();
        MessageHeaders headers = new MessageHeaders(properties);
        Message<String> message = MessageBuilder.createMessage(serial, headers);

        System.out.println("========send=========");
        boolean send = output.send(message);
        System.out.println("send message " + serial + " status " + send);


        return "success";
    }
}
