package com.zw.cloud.netty.web.entity.chat;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class FriendsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private Long sendUserId;

    private Long acceptUserId;

    /**
     * 创建时间
     */
    private LocalDateTime requestDateTime;


}
