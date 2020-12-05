package com.msr.cloudv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 18:06
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacos80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacos80Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory factory(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(15000);
        simpleClientHttpRequestFactory.setReadTimeout(6000);
        return simpleClientHttpRequestFactory;
    }
}
