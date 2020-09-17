package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {

    public static final String order_uri="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumerZk/payment/zk")
    public String payment(){
        String result = restTemplate.getForObject(order_uri+"/payment/zk",String.class);
        return result;
    }
}