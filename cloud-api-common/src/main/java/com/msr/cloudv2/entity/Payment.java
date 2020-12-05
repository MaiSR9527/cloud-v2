package com.msr.cloudv2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/14 22:35
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {

    private Integer id;
    private String name;
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String serial;
}
