package com.msr.cloudv2.payment.service.impl;

import com.msr.cloudv2.payment.dao.PaymentMapper;
import com.msr.cloudv2.entity.Payment;
import com.msr.cloudv2.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:56
 * @version: v1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int insertOne(Payment payment) {
        return paymentMapper.insertOne(payment);
    }

    @Override
    public Payment selectById(Integer id) {
        return paymentMapper.selectById(id);
    }

    @Override
    public List<Payment> getList() {
        return paymentMapper.getList();
    }
}
