package com.msr.cloudv2.order.controller;

import com.msr.cloudv2.order.client.PaymentClient;
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

    @GetMapping("openfeign")
    public Object getByFeign() {
        return paymentClient.getByOpenfeign();
    }


}
