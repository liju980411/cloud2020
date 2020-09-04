package com.atguigu.springcloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//set,get
@Data
//全参
@AllArgsConstructor
//空参
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
