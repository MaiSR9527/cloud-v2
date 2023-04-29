package com.msr.better.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author MaiShuRen
 * @site <a href="https://www.maishuren.top">maiBlog</a>
 * @since 2023-04-29 00:09
 **/
@Data
@Entity
@Table(name = "PROPERTIES")
public class ConfigProperties {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "KEY")
    private String key;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "APPLICATION")
    private String application;
    @Column(name = "PROFILE")
    private String profile;
    @Column(name = "LABEL")
    private String label;
}
