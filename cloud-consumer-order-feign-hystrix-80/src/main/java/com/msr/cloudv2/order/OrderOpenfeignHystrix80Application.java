package com.msr.cloudv2.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/18 16:41
 * @version: v1.0
 */
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class OrderOpenfeignHystrix80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenfeignHystrix80Application.class, args);
    }
}
