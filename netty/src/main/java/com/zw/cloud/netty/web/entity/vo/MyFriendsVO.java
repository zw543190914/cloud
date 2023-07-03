package com.zw.cloud.netty.web.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MyFriendsVO implements Serializable {
    private Long friendUserId;
    private String friendUsername;
    private String friendFaceImage;
    private String friendNickname;

}
