package com.atguigu.springcloud.server;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

public interface IMessageProvider {

    public String send();
}
