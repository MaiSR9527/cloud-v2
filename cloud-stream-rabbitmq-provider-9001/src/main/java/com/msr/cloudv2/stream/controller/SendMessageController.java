package com.msr.cloudv2.stream.controller;

import com.msr.cloudv2.stream.service.IMessageProviderService;
import com.msr.cloudv2.stream.service.impl.MessageProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 13:30
 * @version: v1.0
 */
@RestController
@RequestMapping
public class SendMessageController {

    @Resource
    private IMessageProviderService messageProviderService;

    @GetMapping("/send")
    public String sendMessage() {
        Map<String,Object> map = new HashMap<>(16);
        map.put("header","json");
        return messageProviderService.send(null,map);
    }
}
