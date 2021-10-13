package com.zw.cloud.tools.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Tc implements Serializable {
    private Integer id;

    private Integer one;

    private Integer two;

    private Integer three;

    private Integer four;

    private Integer five;

    private Integer six;

    private Integer seven;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}