package com.atguigu.springcloud.service;

import org.apache.ibatis.annotations.Param;

public interface StorageService {
    void decrease(@Param("productId")Long productId, @Param("count")Integer count);
}
