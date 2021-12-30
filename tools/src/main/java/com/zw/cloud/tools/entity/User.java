package com.zw.cloud.tools.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class User implements Serializable {
    private Long id;

    private String name;

    private Byte age;

    private Date bir;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private String description;

}