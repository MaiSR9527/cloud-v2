package com.msr.cloudv2.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 18:28
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigNacos7788Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigNacos7788Application.class, args);
    }
}
