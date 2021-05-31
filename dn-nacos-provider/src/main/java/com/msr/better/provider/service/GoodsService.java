package com.msr.better.provider.service;

import com.msr.better.provider.mapper.GoodsMapper;
import com.msr.cloudv2.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getAllGoods() {
        return goodsMapper.selectByAll();
    }
}