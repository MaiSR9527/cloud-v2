package com.msr.cloudv2.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 10:13
 * @version: v1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Application.class, args);
    }
}
