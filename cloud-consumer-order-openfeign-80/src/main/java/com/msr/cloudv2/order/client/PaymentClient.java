package com.msr.cloudv2.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 17:34
 * @version: v1.0
 */
@Component
@FeignClient(value = "cloud-consul-payment-service")
public interface PaymentClient {

    @GetMapping("payment/openfeign")
    String getByOpenfeign();

}
