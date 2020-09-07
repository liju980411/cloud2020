package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import javax.annotation.Resource;
//Spring4之后新加入的注解，原来返回json需要@ResponseBody和@Controller配合。
//即@RestController是@ResponseBody和@Controller的组合注解。
@RestController
//允许你在后台使用任意一个日志类库
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/create")
    public CommonResult create(@RequestBody Payment payment){

        System.out.println(payment);
        int result = paymentService.create(payment);
        log.info("******插入结果"+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @RequestMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){

        Payment payment  = paymentService.getPayment(id);
        log.info("******查询结果"+payment);
        if(payment != null){
            return new CommonResult(200,"查询成功",payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询id"+id,null);
        }
    }


}
