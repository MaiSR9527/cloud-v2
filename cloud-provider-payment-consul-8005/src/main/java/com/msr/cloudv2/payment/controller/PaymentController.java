package com.msr.cloudv2.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 15:08
 * @version: v1.0
 */
@RestController
@RequestMapping("payment")
public class PaymentController {

    @GetMapping("consul")
    public Object consulRegistry(){
        return "port: 8005";
    }

    @GetMapping("openfeign")
    public String getByOpenfeign(){
        return "openfeign: 8005";
    }
}

