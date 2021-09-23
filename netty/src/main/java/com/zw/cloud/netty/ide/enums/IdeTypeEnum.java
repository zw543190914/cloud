package com.zw.cloud.netty.ide.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 幂等枚举类
 */
@Getter
@AllArgsConstructor
public enum IdeTypeEnum {


    /**
     * rid 是针对每一次请求的
     */
    RID(1, "RID"),
    /**
     * 根据所有的参数和对应的值
     */
    PARAMS(2, "PARAMS");

    private final Integer index;
    private final String title;
}

