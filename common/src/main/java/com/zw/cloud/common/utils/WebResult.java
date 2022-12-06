package com.zw.cloud.common.utils;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author 镜中水月
 */
public class WebResult<T> implements Serializable {
    private boolean success;
    private T data;
    private Integer code;
    private String msg;

    public WebResult() {
    }

    public WebResult(boolean success, T data, Integer errorCode, String errorMsg) {
        this.success = success;
        this.data = data;
        this.code = errorCode;
        this.msg = errorMsg;
    }

    private WebResult(boolean success) {
        this.success = success;
    }

    public static <T>WebResult<T> success() {
        return new WebResult<>(true);
    }

    public static WebResult<Object> failed() {
        WebResult<Object> failed = new WebResult<>(false);
        failed.code = HttpStatus.HTTP_BAD_REQUEST;
        return failed;
    }

    public static <T> WebResult<T> build(T data) {
        WebResult<T> webResult = new WebResult<>();
        webResult.success = true;
        webResult.data = data;
        return webResult;
    }

    public WebResult<T> withErrorCodeAndMsg(Integer errorCode,String errorMsg) {
        this.code = errorCode;
        this.msg = errorMsg;
        return this;
    }

    public WebResult<T> withErrorCode(Integer errorCode) {
        this.code = errorCode;
        return this;
    }

    public WebResult<T> withErrorMsg(String errorMsg) {
        this.msg = errorMsg;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
