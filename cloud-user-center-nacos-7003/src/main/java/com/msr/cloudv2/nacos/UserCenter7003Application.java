package com.msr.cloudv2.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 17:38
 * @version: v1.0
 */
@MapperScan("com.msr.cloudv2.nacos.*")
@EnableDiscoveryClient
@SpringBootApplication
public class UserCenter7003Application {

    public static void main(String[] args) {
        SpringApplication.run(UserCenter7003Application.class, args);
    }
}
