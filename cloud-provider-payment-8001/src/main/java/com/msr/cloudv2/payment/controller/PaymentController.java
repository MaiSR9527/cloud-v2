package com.msr.cloudv2.payment.controller;

import com.msr.cloudv2.entity.Payment;
import com.msr.cloudv2.entity.Result;
import com.msr.cloudv2.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
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

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("save")
    public Result save(@RequestBody Payment payment) {
        payment.setCreateTime(new Date());
        payment.setSerial(UUID.randomUUID().toString());
        int i = paymentService.insertOne(payment);
        if (1 > 0) {
            return new Result(200, "插入成功", i);
        } else {
            return new Result(444, "插入失败", i);
        }
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Payment payment = paymentService.selectById(id);
        if (payment == null) {
            return new Result(444, "数据不存在", null);
        }
        return new Result(200, "查询成功", payment);
    }

    @GetMapping("list")
    public Result getList() {
        List<Payment> list = paymentService.getList();
        return new Result(200, "查询成功-8001", list);
    }

    @GetMapping("discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("service: " + service);
        }
        int order = discoveryClient.getOrder();
        System.out.println("order: " + order);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
}
