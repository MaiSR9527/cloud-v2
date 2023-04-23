package com.msr.better.consul.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2023-04-16 23:46
 **/
@RestController
@RequestMapping("provider")
public class ProviderController {

    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("info")
    public String info() {
        return "provider name: " + appName + ", port: " + port;
    }
}
