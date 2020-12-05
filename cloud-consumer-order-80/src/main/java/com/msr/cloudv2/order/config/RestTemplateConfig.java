package com.msr.cloudv2.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 23:31
 * @version: v1.0
 */
@Configuration
public class RestTemplateConfig {

    @Bean
//    @LoadBalanced
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
