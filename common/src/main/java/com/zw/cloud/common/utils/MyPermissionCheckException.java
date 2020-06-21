package com.zw.cloud.common.utils;

public class MyPermissionCheckException extends RuntimeException {

    private int code;
    private String msg;

    public MyPermissionCheckException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyPermissionCheckException(String msg){
        super(msg);
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
