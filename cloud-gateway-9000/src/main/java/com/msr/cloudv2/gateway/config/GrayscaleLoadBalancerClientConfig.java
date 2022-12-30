package com.msr.cloudv2.gateway.config;

import com.msr.cloudv2.gateway.filter.GrayscaleReactiveLoadBalancerClientFilter;
import com.msr.cloudv2.gateway.rule.GrayVersionLoadBalancer;
import com.msr.cloudv2.gateway.rule.GrayscaleLoadBalancer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.config.GatewayReactiveLoadBalancerClientAutoConfiguration;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2022-12-28 23:10
 **/
@Configuration
@EnableConfigurationProperties(GatewayLoadBalancerProperties.class)
@ConditionalOnProperty(value = "project.grayscale.enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore(GatewayReactiveLoadBalancerClientAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class GrayscaleLoadBalancerClientConfig {

    @Bean
    public GrayscaleLoadBalancer  grayLoadBalancer(DiscoveryClient discoveryClient) {
        return new GrayVersionLoadBalancer(discoveryClient);
    }

    @Bean
    public ReactiveLoadBalancerClientFilter gatewayLoadBalancerClientFilter(GrayscaleLoadBalancer grayLoadBalancer,
                                                                            GatewayLoadBalancerProperties properties) {
        return new GrayscaleReactiveLoadBalancerClientFilter(properties, grayLoadBalancer);
    }
}
