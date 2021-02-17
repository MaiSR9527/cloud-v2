package com.msr.cloudv2.order.lbRule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 16:49
 * @version: v1.0
 */
public interface MyLoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
