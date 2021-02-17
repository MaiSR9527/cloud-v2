package com.msr.cloudv2.order.lbRule;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基于Nacos示例权重的负载均衡策略
 *
 * @author MaiShuRen
 * @version v1.0
 * @date 2021/1/30 16:24
 */
@Slf4j
public class NacosWeightRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // 读取配置文件，初始化NacosWeightedRule
    }

    @Override
    public Server choose(Object key) {
        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
        log.info("负载均衡器：{}", loadBalancer);
        // 要请求的微服务的名称
        String name = loadBalancer.getName();
        // 实现负载均衡算法
        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
        // 使用Nacos自带的基于权重的负载均衡算法
        try {
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("port:{}, instance:{}", instance.getPort(), instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
