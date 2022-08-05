package com.zw.cloud.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderTbl implements Serializable {
    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;

    private static final long serialVersionUID = 1L;


}