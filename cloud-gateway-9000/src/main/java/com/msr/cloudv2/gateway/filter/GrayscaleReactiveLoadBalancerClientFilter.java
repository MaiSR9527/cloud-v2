package com.msr.cloudv2.gateway.filter;

import com.msr.cloudv2.gateway.rule.GrayscaleLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.DelegatingServiceInstance;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * 灰度过滤器
 *
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2022-12-30 18:19
 **/
@Slf4j
public class GrayscaleReactiveLoadBalancerClientFilter extends ReactiveLoadBalancerClientFilter {

    private static final int LOAD_BALANCER_CLIENT_FILTER_ORDER = 10150;
    private static final String LB = "lb";
    private GatewayLoadBalancerProperties properties;

    private GrayscaleLoadBalancer grayLoadBalancer;

    public GrayscaleReactiveLoadBalancerClientFilter(GatewayLoadBalancerProperties properties,
                                                     GrayscaleLoadBalancer grayLoadBalancer) {
        super(null, properties, null);
        this.properties = properties;
        this.grayLoadBalancer = grayLoadBalancer;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI url = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        String schemePrefix = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR);
        if (url == null) {
            return chain.filter(exchange);
        }
        if (!LB.equals(url.getScheme()) && !LB.equals(schemePrefix)) {
            return chain.filter(exchange);
        }

        // preserve the original url
        ServerWebExchangeUtils.addOriginalRequestUrl(exchange, url);

        if (log.isTraceEnabled()) {
            log.trace(ReactiveLoadBalancerClientFilter.class.getSimpleName() + " url before: " + url);
        }
        // 这里呢会进行调用真正的负载均衡
        return choose(exchange).doOnNext(response -> {
            if (!response.hasServer()) {
                throw NotFoundException.create(properties.isUse404(),
                        "Unable to find instance for " + url.getHost());
            }

            URI uri = exchange.getRequest().getURI();

            // if the `lb:<scheme>` mechanism was used, use `<scheme>` as the default,
            // if the loadbalancer doesn't provide one.
            String overrideScheme = null;
            if (schemePrefix != null) {
                overrideScheme = url.getScheme();
            }

            DelegatingServiceInstance serviceInstance = new DelegatingServiceInstance(
                    response.getServer(), overrideScheme);

            URI requestUrl = LoadBalancerUriTools.reconstructURI(serviceInstance, uri);

            if (log.isTraceEnabled()) {
                log.trace("LoadBalancerClientFilter url chosen: " + requestUrl);
            }
            exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, requestUrl);
        }).then(chain.filter(exchange));
    }

    @Override
    public int getOrder() {
        return LOAD_BALANCER_CLIENT_FILTER_ORDER;
    }

    private Mono<Response<ServiceInstance>> choose(ServerWebExchange exchange) {
        URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        ServiceInstance serviceInstance = grayLoadBalancer.choose(uri.getHost(), exchange.getRequest());
        return Mono.just(new DefaultResponse(serviceInstance));
    }
}
