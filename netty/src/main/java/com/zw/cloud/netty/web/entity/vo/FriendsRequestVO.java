package com.zw.cloud.netty.web.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FriendsRequestVO implements Serializable {

    private Long sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;

}