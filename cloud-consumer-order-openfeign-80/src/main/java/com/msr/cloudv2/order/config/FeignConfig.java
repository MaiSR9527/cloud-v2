package com.msr.cloudv2.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: feign日志增强
 * @author: MaiShuRen
 * @date: 2020/4/15 18:07
 * @version: v1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return  Logger.Level.FULL;
    }
}
