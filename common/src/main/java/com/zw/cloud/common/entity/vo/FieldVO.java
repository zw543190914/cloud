package com.zw.cloud.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class FieldVO implements Serializable {
    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 属性类型
     */
    private String fieldType;

    /**
     * 属性注释
     */
    private String describe;


}
