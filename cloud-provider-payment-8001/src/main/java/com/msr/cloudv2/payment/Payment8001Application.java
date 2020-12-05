package com.msr.cloudv2.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:18
 * @version: v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Payment8001Application {

    public static void main(String[] args) {
        SpringApplication.run(Payment8001Application.class,args);
    }
}
