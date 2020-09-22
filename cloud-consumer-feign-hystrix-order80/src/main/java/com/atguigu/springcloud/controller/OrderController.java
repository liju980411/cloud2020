package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //全局的
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return orderService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/no/{id}")
    @HystrixCommand
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandlend",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return orderService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandlend(Integer id){
        return "线程池："+Thread.currentThread().getName()+"我是80,8001无响应，嘲讽 就这，我还没认真,id： "+id;
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }
}
