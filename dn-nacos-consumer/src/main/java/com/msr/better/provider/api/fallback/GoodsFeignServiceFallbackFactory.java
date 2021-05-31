package com.msr.better.provider.api.fallback;


import com.msr.better.provider.api.GoodsServiceApi;
import com.msr.cloudv2.entity.Goods;
import feign.hystrix.FallbackFactory;

import java.util.ArrayList;

/**
 * Fallback处理
 */
public class GoodsFeignServiceFallbackFactory implements FallbackFactory<GoodsServiceApi> {

    @Override
    public GoodsServiceApi create(Throwable throwable) {
        return () -> new ArrayList<Goods>();
    }
}