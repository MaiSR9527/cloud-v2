package com.msr.cloudv2.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 13:21
 * @version: v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StreamProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamProviderApplication.class, args);
    }
}
