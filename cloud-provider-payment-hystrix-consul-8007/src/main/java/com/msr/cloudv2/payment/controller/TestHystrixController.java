package com.msr.cloudv2.payment.controller;

import com.msr.cloudv2.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/18 16:12
 * @version: v1.0
 */
@RestController
@RequestMapping("payment")
public class TestHystrixController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("ok")
    public String testHystrix() {
        return paymentService.paymentInfo((int) Thread.currentThread().getId());
    }

    @GetMapping("timeout")
    public String testHystrixTimeout() {
        return paymentService.timeOut((int) Thread.currentThread().getId());
    }

    @GetMapping("/circuit/{id}")
    public String circuit(@PathVariable Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }

}
