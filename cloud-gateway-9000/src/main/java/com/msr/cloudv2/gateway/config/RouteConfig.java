package com.msr.cloudv2.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/20 17:20
 * @version: v1.0
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRoute(RouteLocatorBuilder builder){
        return builder.routes().
                route("news",r->r.path("/news")
                        .uri("https://www.baidu.com/"))
                .build();
    }
}
