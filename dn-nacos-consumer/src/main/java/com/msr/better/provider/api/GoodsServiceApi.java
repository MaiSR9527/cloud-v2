package com.msr.better.provider.api;

import com.msr.better.provider.api.configuration.FeignConfiguration;
import com.msr.better.provider.api.fallback.GoodsFeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2021-05-31 23:19
 **/
@FeignClient(name = "dn-nacos-provider",
        fallbackFactory = GoodsFeignServiceFallbackFactory.class,
        configuration = FeignConfiguration.class)
public interface GoodsServiceApi {

    @RequestMapping("/goods")
    public Object getAllGoods();
}
