package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SentinelExceptionHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        //BlockException 异常接口,包含Sentinel的五个异常
        // FlowException 限流异常
        // DegradeException 降级异常
        // ParamFlowException 参数限流异常
        // AuthorityException 授权异常
        // SystemBlockException 系统负载异常
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ResponseData data = null;
        if (e instanceof FlowException) {
            data = new ResponseData(-1, "流控规则被触发......");
        } else if (e instanceof DegradeException) {
            data = new ResponseData(-2, "降级规则被触发...");
        } else if (e instanceof AuthorityException) {
            data = new ResponseData(-3, "授权规则被触发...");
        } else if (e instanceof ParamFlowException) {
            data = new ResponseData(-4, "热点规则被触发...");
        } else if (e instanceof SystemBlockException) {
            data = new ResponseData(-5, "系统规则被触发...");
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(data));
    }
}
/**
 * 定义下返回类，如果项目有自己的返回类，那么就用自己项目定义的返回类统一处理，这里是为了方便测试
 */
class ResponseData {
    private int code;
    private String message;

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}