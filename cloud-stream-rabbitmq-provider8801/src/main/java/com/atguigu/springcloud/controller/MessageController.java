package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.server.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {
    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/send")
    public String send(){
        return iMessageProvider.send();
    }
}
