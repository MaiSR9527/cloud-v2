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
@FeignClient(value = "cloud-provider-payment-hystrix-service", fallback = FallBackService.class)
public interface PaymentClient {

    @GetMapping("payment/ok")
    String getOk();

    @GetMapping("payment/timeout")
    String getTimeout();

}
