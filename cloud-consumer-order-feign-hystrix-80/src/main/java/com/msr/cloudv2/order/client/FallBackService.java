package com.msr.cloudv2.order.client;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/18 18:05
 * @version: v1.0
 */
@Component
public class FallBackService implements PaymentClient {
    @Override
    public String getOk() {
        return "fallback for ok method";
    }

    @Override
    public String getTimeout() {
        return "fallback for timeout method";
    }
}
