package com.msr.cloudv2.myconfig;

import com.msr.cloudv2.order.lb.MyRoundRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/15 16:04
 * @version: v1.0
 */
@Configuration
public class MyRuleConfig {

    @Bean
    public IRule rule() {
        return (IRule) new MyRoundRule();
    }
}
