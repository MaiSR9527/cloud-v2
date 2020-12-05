package com.msr.cloudv2.order.controller;

import com.msr.cloudv2.order.client.PaymentClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 17:30
 * @version: v1.0
 */
@RestController
@RequestMapping("order")
public class OrderOpenFeignController {

    @Autowired
    private PaymentClient paymentClient;


    @GetMapping("timeout")
    public String getTimeout() {
        return paymentClient.getTimeout();
    }

    @GetMapping("ok")
    public String getOK() {
        return paymentClient.getOk();
    }

    public String paymentTimeoutHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "\t" + "80 is timeout or error " + id + " /(ㄒoㄒ)/~~";

    }

    public String defaultFallback(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "\t" + "default error " + id + " /(ㄒoㄒ)/~~";

    }
}
