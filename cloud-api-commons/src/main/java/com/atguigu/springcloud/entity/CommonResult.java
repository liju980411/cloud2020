package com.atguigu.springcloud.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//set,get
@Data
//全参
@AllArgsConstructor
//空参
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;//数字类型错误编码
    private String message;//String类型消息
    private T data;//返回的的是Payment类就是Payment类；返回的是order，就是order；

    //两个参数的构造方法
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
