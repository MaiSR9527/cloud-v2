package com.msr.better.consul.consumer.controller;

import com.msr.better.consul.consumer.api.ConsulProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2023-04-17 00:01
 **/
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private ConsulProviderFeignClient providerFeignClient;

    @GetMapping("getProviderInfo")
    public Object getInfo() {
        return providerFeignClient.getProviderInfo();
    }
}
