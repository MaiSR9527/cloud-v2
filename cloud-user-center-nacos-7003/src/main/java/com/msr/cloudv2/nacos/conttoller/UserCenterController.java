package com.msr.cloudv2.nacos.conttoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 17:38
 * @version: v1.0
 */
@RestController
@RequestMapping("nacos")
public class UserCenterController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("call7005")
    public Object testNacos() {
        List<String> services = discoveryClient.getServices();
        List<Object> list = new ArrayList<>();
        services.stream().distinct().forEach(e -> list.add(discoveryClient.getInstances(e)));
        return list;
    }

    @GetMapping("toCall7005")
    public Object call() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-user-center-provider-service");
        List<ServiceInstance> serviceInstances = instances.stream().filter(e -> e.getPort() == 7005).collect(Collectors.toList());
        ServiceInstance serviceInstance = serviceInstances.get(0);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri.toString()+"nacos/test", String.class);
    }


    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        return new RestTemplate();
    }
}
