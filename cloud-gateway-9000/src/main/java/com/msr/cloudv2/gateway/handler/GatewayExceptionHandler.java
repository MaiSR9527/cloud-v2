package com.msr.cloudv2.gateway.handler;

import com.msr.cloudv2.basic.handler.AbstractExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2022-04-28
 */
@Configuration
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GatewayExceptionHandler extends AbstractExceptionHandler {
}
