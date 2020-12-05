package com.msr.cloudv2.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 17:29
 * @version: v1.0
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OrderOpenFeign80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeign80Application.class, args);
    }
}
