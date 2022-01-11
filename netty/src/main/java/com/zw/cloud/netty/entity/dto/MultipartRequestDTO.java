package com.zw.cloud.netty.entity.dto;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.multipart.FileUpload;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
public class MultipartRequestDTO implements Serializable {

    private Map<String, FileUpload> fileUploads;
    private JSONObject params;

}
