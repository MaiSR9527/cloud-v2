package com.msr.cloudv2.gateway.filter;

import cn.hutool.core.util.IdUtil;
import com.msr.cloudv2.basic.constants.ContextConstants;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 生成日志链路追踪，并传入header中
 *
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2022-12-30 18:29
 **/
@Component
public class TraceFilter implements WebFilter, Ordered {
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String traceId = IdUtil.fastSimpleUUID();
        MDC.put(ContextConstants.LOG_TRACE_ID, traceId);
        ServerHttpRequest httpRequest = exchange.getRequest().mutate()
                .headers(h -> h.add(ContextConstants.TRACE_ID_HEADER, traceId))
                .build();
        return chain.filter(exchange.mutate().request(httpRequest).build());
    }
}
