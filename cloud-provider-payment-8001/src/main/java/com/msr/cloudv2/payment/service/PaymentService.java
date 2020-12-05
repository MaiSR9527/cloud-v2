package com.msr.cloudv2.payment.service;

import com.msr.cloudv2.entity.Payment;

import java.util.List;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:55
 * @version: v1.0
 */
public interface PaymentService {

    int insertOne(Payment payment);

    Payment selectById(Integer id);

    List<Payment> getList();
}
