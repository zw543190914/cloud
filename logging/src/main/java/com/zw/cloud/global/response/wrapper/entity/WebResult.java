package com.zw.cloud.global.response.wrapper.entity;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;

public class WebResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T data;

    public WebResult(Integer code){
        this.code = code;
    }

    public WebResult(Integer code,T data){
        this.code = code;
        this.data = data;
    }

    public static <T> WebResult<T> success(){
        return new WebResult<T>(HttpStatus.HTTP_OK);
    }

    public static <T> WebResult<T> success(T data){
        return new WebResult<T>(HttpStatus.HTTP_OK,data);
    }
    public WebResult<T> withData(T data) {
        this.data = data;
        return this;
    }
    public static <T> WebResult<T> fail(){
        return new WebResult<T>(HttpStatus.HTTP_INTERNAL_ERROR);
    }
    public static <T> WebResult<T> fail(String message){
        return fail(HttpStatus.HTTP_INTERNAL_ERROR,message);
    }
    public static <T> WebResult<T> fail(Integer code,String message){
        WebResult<T> webResult = new WebResult<T>(code);
        webResult.setMessage(message);
        return webResult;
    }

    public void setCode(Integer code){
        this.code = code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setData(T data){
        this.data = data;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
    public T getData(){
        return this.data;
    }
    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}

