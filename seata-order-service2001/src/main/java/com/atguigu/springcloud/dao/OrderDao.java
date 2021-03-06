package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    //创建订单
    void create(Order order);
    //更改订单状态 0>>1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
