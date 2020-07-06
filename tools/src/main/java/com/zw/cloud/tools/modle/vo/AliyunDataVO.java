package com.zw.cloud.tools.modle.vo;

import java.io.Serializable;

public class AliyunDataVO implements Serializable {
    private String Data;
    private String RequestId;
    private String Success;


    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }
}
