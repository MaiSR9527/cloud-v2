package com.msr.cloudv2.order;

import com.msr.cloudv2.myconfig.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 23:28
 * @version: v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "cloud-consul-payment-service", configuration = MyRuleConfig.class)
//@RibbonClients(defaultConfiguration = MyRuleConfig.class)  全局配置
public class Order80Application {

    public static void main(String[] args) {
        SpringApplication.run(Order80Application.class, args);
    }
}
