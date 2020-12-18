package com.atguigu.springcloud.server.impl;

import com.atguigu.springcloud.server.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class MessageProvider implements IMessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String send = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(send).build());
        System.out.println(send);
        return null;
    }
}
