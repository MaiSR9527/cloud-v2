package com.msr.cloudv2.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 17:38
 * @version: v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Nacos7003Application {

    public static void main(String[] args) {
        SpringApplication.run(Nacos7003Application.class, args);
    }
}
