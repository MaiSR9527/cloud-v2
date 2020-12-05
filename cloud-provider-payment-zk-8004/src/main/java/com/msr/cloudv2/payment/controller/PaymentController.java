package com.msr.cloudv2.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:57
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {

    @GetMapping("zk")
    public Object zookeeper() {
        return "zookeeper:" + UUID.randomUUID().toString();
    }
}
