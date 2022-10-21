package com.zw.cloud.mybatis.plus.enums;

import com.zw.cloud.common.exception.BizException;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum ExceptionTypeEnum {
    NORMAL(0, "正常数据"),
    QUALITY_EXCEPTION(1, "质量异常"),
    OPERATE_EXCEPTION(2, "操作异常"),
    OPERATE_QUALITY_EXCEPTION(3, "质量异常、操作异常"),
    ASSISTANT_EXCEPTION(4, "助剂异常"),
    CRAFT_EXCEPTION(5, "工艺异常");

    private Integer code;
    private String desc;

    ExceptionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ExceptionTypeEnum get(Integer code) {
        ExceptionTypeEnum[] ExceptionTypeEnums = values();
        for (ExceptionTypeEnum exceptionTypeEnum : ExceptionTypeEnums) {
            if (Objects.equals(exceptionTypeEnum.getCode(), code)) {
                return exceptionTypeEnum;
            }
        }
        throw new BizException(MessageFormat.format("枚举类型{0}不存在", code));
    }
}
