package com.msr.cloudv2.entity;

import lombok.Builder;
import lombok.Data;

@Data //get set toString hashCode ......
@Builder //生成构建器模式
public class RestObject {

    private int statusCode;

    private String statusMessage;

    private Object data;
}