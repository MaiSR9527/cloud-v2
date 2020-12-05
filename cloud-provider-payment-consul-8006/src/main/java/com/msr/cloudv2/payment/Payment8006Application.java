package com.msr.cloudv2.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 15:38
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8006Application {

    public static void main(String[] args) {
        SpringApplication.run(Payment8006Application.class, args);
    }
}
