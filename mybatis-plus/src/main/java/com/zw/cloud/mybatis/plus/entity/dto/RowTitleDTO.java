package com.zw.cloud.mybatis.plus.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RowTitleDTO implements Serializable {
    private String label;
    private String key;
}
