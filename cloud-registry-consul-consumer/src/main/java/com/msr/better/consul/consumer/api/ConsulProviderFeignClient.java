package com.msr.better.consul.consumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2023-04-17 00:02
 **/
@FeignClient("cloud-registry-consul-provider")
public interface ConsulProviderFeignClient {

    @GetMapping(value = "provider/info")
    String getProviderInfo();
}
