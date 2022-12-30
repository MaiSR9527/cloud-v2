package com.msr.cloudv2.gateway.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 灰度路由
 *
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2022-12-28 23:19
 **/
public interface GrayscaleLoadBalancer {

    /**
     * 根据serviceId选择可用的服务
     *
     * @param serviceId 服务ID
     * @param request   http请求
     * @return
     */
    ServiceInstance choose(String serviceId, ServerHttpRequest request);
}
