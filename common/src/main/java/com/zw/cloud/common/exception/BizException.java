package com.zw.cloud.common.exception;

public class BizException extends RuntimeException {
    private Integer code;

    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BizException(String message) {
        super(message);
        this.code = 500;
    }
}
