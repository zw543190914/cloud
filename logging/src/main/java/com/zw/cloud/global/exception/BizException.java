package com.zw.cloud.global.exception;

public class BizException extends RuntimeException {
    private Integer code;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String message) {
        super(message);
        this.code = 500;
    }
}
