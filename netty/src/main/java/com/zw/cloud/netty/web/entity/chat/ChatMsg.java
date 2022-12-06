package com.zw.cloud.netty.web.entity.chat;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 消息发送人
     */
    private Long sendUserId;
    /**
     * 消息接收人
     */
    private Long acceptUserId;
    /**
     * 消息群组
     */
    private Long acceptGroupId;
    /**
     * 消息内容
     */
    private String msg;

    /**
     * 消息是否签收(未签收:0,签收:1)
     */
    private Integer signFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
