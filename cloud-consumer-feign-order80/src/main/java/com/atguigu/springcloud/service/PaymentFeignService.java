package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

//    @RequestMapping("/payment/get/{id}")
//    public CommonResult getPayment(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String timeout();
}
