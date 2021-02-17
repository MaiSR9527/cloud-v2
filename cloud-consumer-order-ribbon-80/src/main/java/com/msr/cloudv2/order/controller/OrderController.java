package com.msr.cloudv2.order.controller;

import com.msr.cloudv2.entity.Result;
import com.msr.cloudv2.order.lbRule.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 23:28
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MyLoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("list")
    public Result getAll() {
        return restTemplate.getForObject("http://CLOUD-PROVIDER-PAYMENT-SERVICE/payment/list", Result.class);
    }

    @GetMapping("consul/get")
    public Object consulRegistry() {
        return restTemplate.getForObject("http://cloud-consul-payment-service/payment/consul", String.class);
    }

    @GetMapping("consul/get/lb")
    public Object getByMyLb(HttpServletRequest request) {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-consul-payment-service");
        if (instances == null || instances.size() == 0) {
            return "null services";
        }
        log.info("host request : {},addr : {}", request.getRemoteHost(),request.getRemoteAddr());
        ServiceInstance instance = loadBalancer.instance(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/consul", String.class);
    }

}
