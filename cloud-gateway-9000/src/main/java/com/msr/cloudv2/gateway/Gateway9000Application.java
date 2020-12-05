package com.msr.cloudv2.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/20 16:55
 * @version: v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Gateway9000Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway9000Application.class, args);
    }
}
