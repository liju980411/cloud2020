package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import javax.annotation.Resource;
import java.util.List;

//Spring4之后新加入的注解，原来返回json需要@ResponseBody和@Controller配合。
//即@RestController是@ResponseBody和@Controller的组合注解。
@RestController
//允许你在后台使用任意一个日志类库
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {

        System.out.println(payment);
        int result = paymentService.create(payment);
        log.info("******插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @RequestMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPayment(id);
        log.info("******查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询id" + id, null);
        }
    }

    @RequestMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String service:services){
            log.info(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances){
            log.info(instance.getServiceId()+instance.getHost()+instance.getInstanceId()+instance.getPort()+instance.getUri());
        }
        return this.discoveryClient;
    }

}