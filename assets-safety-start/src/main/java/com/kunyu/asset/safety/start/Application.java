/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.asset.safety.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description 启动类
 * @author yangliu
 * @date 2023-08-02
 */
@SpringBootApplication(scanBasePackages = "com.kunyu")
@MapperScan("com.kunyu.assets.safety.infrastructure")
@EnableDiscoveryClient
public class Application {
    /**
     * @description 启动方法
     * @param args args
     * @return null null
     * @author yangliu
     * @date 2023/8/2
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
