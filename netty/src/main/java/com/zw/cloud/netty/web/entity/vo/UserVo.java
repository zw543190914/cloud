package com.zw.cloud.netty.web.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserVo implements Serializable {
    private String id;

    private String username;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;

    private String accessToken;

}