package com.zw.cloud.mybatis.plus.enums;

import lombok.Getter;

@Getter
public enum DeleteEnum {
    YES(1, "删除"),
    NO(0, "未删除");

    private final Integer status;
    private final String statusName;

    DeleteEnum(Integer status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }
}
