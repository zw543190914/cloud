package com.zw.cloud.activiti.entity;

import java.io.Serializable;

public class ActGeBytearrayVO extends ActGeBytearray implements Serializable {
    private String byteStr;

    public String getByteStr() {
        return byteStr;
    }

    public void setByteStr(String byteStr) {
        this.byteStr = byteStr;
    }
}
