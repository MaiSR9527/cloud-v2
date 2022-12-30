package com.msr.cloudv2.gateway.rule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.msr.cloudv2.basic.constants.ContextConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.Map;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2022-12-30 16:48
 **/
@Slf4j
@AllArgsConstructor
public class GrayVersionLoadBalancer implements GrayscaleLoadBalancer {

    private DiscoveryClient discoveryClient;

    @Override
    public ServiceInstance choose(String serviceId, ServerHttpRequest request) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        // 注册中心无实列
        if (CollectionUtil.isEmpty(instances)) {
            log.warn("{} not found in registry", serviceId);
            throw new NotFoundException(serviceId + "not found in registry");
        }

        // 无灰度版本，返回随机实列
        String grayVersion = request.getHeaders().getFirst(ContextConstants.GRAY_VERSION);
        if (StrUtil.isBlank(grayVersion)) {
            return instances.get(RandomUtil.randomInt(instances.size()));
        }
        for (ServiceInstance instance : instances) {
            Map<String, String> metadata = instance.getMetadata();
            if (StrUtil.equalsIgnoreCase(grayVersion, metadata.get(ContextConstants.GRAY_VERSION))) {
                log.debug("gray version is matching version is {}", grayVersion);
                return instance;
            }
        }
        // 没有匹配到灰度版本，随机返回
        return instances.get(RandomUtil.randomInt(instances.size()));
    }
}
