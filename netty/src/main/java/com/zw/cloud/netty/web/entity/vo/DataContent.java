package com.zw.cloud.netty.web.entity.vo;

import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DataContent implements Serializable {
    /**
     * 动作类型
     */
    private Integer action;
    /**
     * 用户的聊天内容
     */
    private ChatMsg chatMsg;
    /**
     * 扩展字段
     */
    private String extand;

}
