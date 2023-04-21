package com.zw.cloud.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "user_info_0", autoResultMap = true)
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 464505629765578240L;
    
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate bir;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer age;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String description;

    @TableField(typeHandler = JsonTypeHandler.class,updateStrategy = FieldStrategy.IGNORED)
    private Object other;

    @TableLogic
    private int deleted;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String orgCode;

}

