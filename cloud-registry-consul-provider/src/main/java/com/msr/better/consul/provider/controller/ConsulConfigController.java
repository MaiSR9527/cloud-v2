package com.msr.better.consul.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2023-04-23 23:48
 **/
@RefreshScope
@RestController
@RequestMapping
public class ConsulConfigController {

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        return hello;
    }
}
