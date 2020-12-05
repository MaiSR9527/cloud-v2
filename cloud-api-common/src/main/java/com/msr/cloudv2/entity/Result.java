package com.msr.cloudv2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:37
 * @version: v1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result implements Serializable {

    private Integer code;
    private String message;
    private Object data;
}
