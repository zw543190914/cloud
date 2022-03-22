package com.zw.cloud.common.utils;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class WebResult<T> implements Serializable {
    private boolean success;
    private T data;
    private Integer errorCode;
    private String errorMsg;

    public WebResult() {
    }

    public WebResult(boolean success, T data, Integer errorCode, String errorMsg) {
        this.success = success;
        this.data = data;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private WebResult(boolean success) {
        this.success = success;
    }

    public static WebResult<Object> success() {
        return new WebResult<>(true);
    }

    public static WebResult<Object> failed() {
        WebResult<Object> failed = new WebResult<>(false);
        failed.errorCode = HttpStatus.HTTP_BAD_REQUEST;
        return failed;
    }

    public static <T> WebResult<T> build(T data) {
        WebResult<T> webResult = new WebResult<>();
        webResult.success = true;
        webResult.data = data;
        return webResult;
    }

    public WebResult withErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public WebResult withErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public WebResult<T> withData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Object getData() {
        return this.data;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
