package com.msr.cloudv2.gateway.fallback;

import com.msr.cloudv2.basic.exption.SysStatusCode;
import com.msr.cloudv2.basic.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 响应超时熔断处理器
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">我的博客</a>
 * @since 2022-12-28 22:36
 **/
@RestController
@Slf4j
public class FallbackController {

    @RequestMapping("/fallback")
    public Mono<R> fallback(ServerWebExchange exchange) {
        log.warn("接口调用失败！");
        return Mono.just(R.validFail(SysStatusCode.SYSTEM_TIMEOUT));
    }
}
