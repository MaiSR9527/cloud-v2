package com.msr.cloudv2.payment.controller;

import com.msr.cloudv2.entity.Payment;
import com.msr.cloudv2.entity.Result;
import com.msr.cloudv2.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

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
        return new Result(200, "查询成功-8002", list);
    }
}
