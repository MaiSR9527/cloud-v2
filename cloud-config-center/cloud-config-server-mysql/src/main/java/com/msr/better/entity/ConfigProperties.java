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
@Table(name = "properties")
public class ConfigProperties {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "config_key")
    private String configKey;
    @Column(name = "config_value")
    private String configValue;
    @Column(name = "application")
    private String application;
    @Column(name = "config_profile")
    private String configProfile;
    @Column(name = "label")
    private String label;
}
