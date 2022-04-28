package com.msr.cloudv2.gateway.handler;

import com.msr.cloudv2.basic.handler.AbstractExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GatewayExceptionHandler extends AbstractExceptionHandler {
}
