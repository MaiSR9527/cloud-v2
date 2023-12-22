package com.msr.better.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-12-22
 * @author: maisrcn@qq.com
 */
@Component
@ConfigurationProperties(prefix = "top.maishuren.cloud")
public class ConfigProperties {
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
