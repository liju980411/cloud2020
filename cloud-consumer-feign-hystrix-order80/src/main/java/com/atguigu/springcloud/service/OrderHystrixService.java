package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderHystrixService implements OrderService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "OrderService fallback paymentInfo_OK o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "OrderService fallback paymentInfo_TimeOut o(╥﹏╥)o";
    }
}
