package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dao.AccountDao;
import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping("/seata/account")
    public CommonResult account(@RequestParam("userId")Long userId, @RequestParam("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"c扣减账户余额结束");
    }
}
