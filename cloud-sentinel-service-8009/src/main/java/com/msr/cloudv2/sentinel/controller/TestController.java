package com.msr.cloudv2.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/5/4 15:12
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("sentinel")
public class TestController {


    @GetMapping("testA")
    public String testA() throws InterruptedException {
        log.info("come in ...");
        return "test ---> A";
    }

    @GetMapping("testB")
    public String testB(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test ---> B";
    }
}


