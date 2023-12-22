package com.msr.better.controller;

import com.msr.better.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2023-12-22
 * @author: maisrcn@qq.com
 */
@RestController
@RequestMapping("/client/config")
public class ConfigController {

    @Autowired
    private ConfigProperties configProperties;

    // @Value("${top.maishuren.cloud.config}")
    // private String value;

    @GetMapping
    public Object getConfigFromDB() {
        return configProperties.getConfig();
    }
}
