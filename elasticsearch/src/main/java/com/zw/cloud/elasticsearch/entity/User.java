package com.zw.cloud.elasticsearch.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date bir;

    private String name;

    private Byte age;

    private String password;

    private String description;

    private String tel;

    private Integer status;

    private Object other;

}
