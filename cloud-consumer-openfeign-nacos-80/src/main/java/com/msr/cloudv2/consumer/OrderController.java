package com.msr.cloudv2.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 18:05
 * @version: v1.0
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("test")
    public String nacosRpc(){
        return restTemplate.getForObject("http://cloud-nacos-provider-service/nacos/test", String.class);
    }
}
