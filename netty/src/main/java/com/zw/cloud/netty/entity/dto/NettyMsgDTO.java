package com.zw.cloud.netty.entity.dto;

import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class NettyMsgDTO implements Serializable {

    /**
     * 消息标签
     * EnumNettyMsgTag
     */
    private Integer tag = EnumNettyMsgTag.PUSH.getKey();
    /**
     * 消息内容
     */
    private String data;

    /**
     * 消息发送者身份标识
     */
    private String userId;

    /**
     * 发往的目标组id
     */
    private String targetGroupId;

    /**
     * 发往的目标 targetUserId
     */
    private String targetUserId;

}
