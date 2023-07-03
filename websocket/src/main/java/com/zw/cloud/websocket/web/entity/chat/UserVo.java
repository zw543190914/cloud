package com.zw.cloud.websocket.web.entity.chat;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserVo implements Serializable {
    private Long id;

    private String username;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;

    private String accessToken;

}