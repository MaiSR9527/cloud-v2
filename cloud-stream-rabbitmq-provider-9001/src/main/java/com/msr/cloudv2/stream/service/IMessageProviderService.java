package com.msr.cloudv2.stream.service;

import java.util.Map;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 13:22
 * @version: v1.0
 */
public interface IMessageProviderService {

    String send(Object object, Map<String, Object> properties);
}
