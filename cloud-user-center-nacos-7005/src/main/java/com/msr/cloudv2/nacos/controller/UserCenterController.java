package com.msr.cloudv2.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 17:38
 * @version: v1.0
 */
@RestController
@RequestMapping("nacos")
public class UserCenterController {


    @GetMapping("test")
    public String testNacos() {
        return "welcome to use nacos port ："+7005;
    }
}
