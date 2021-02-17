package com.msr.cloudv2.order.lbRule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 16:21
 * @version: v1.0
 */
@Component
@Slf4j
public class MyRoundRule implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Autowired
    private DiscoveryClient discoveryClient;

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("访问次数 next : {}", next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
