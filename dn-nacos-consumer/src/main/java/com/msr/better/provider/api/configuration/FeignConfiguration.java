package com.msr.better.provider.api.configuration;

import com.msr.better.provider.api.fallback.EchoFeignServiceFallbackFactory;
import com.msr.better.provider.api.fallback.GoodsFeignServiceFallbackFactory;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {

    @Bean
    public EchoFeignServiceFallbackFactory echoFeignServiceFallbackFactory() {
        return new EchoFeignServiceFallbackFactory();
    }

    @Bean
    public GoodsFeignServiceFallbackFactory goodsFeignServiceFallbackFactory() {
        return new GoodsFeignServiceFallbackFactory();
    }
}