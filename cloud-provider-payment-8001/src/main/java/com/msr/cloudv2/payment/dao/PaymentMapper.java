package com.msr.cloudv2.payment.dao;

import com.msr.cloudv2.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:40
 * @version: v1.0
 */
@Mapper
public interface PaymentMapper {

    int insertOne(Payment payment);

    Payment selectById(@Param("id")Integer id);

    List<Payment> getList();
}
