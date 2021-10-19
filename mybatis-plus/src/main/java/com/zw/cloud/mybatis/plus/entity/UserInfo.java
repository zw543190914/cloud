package com.zw.cloud.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.io.Serializable;

@Getter
@Setter
@TableName(value = "user_info", autoResultMap = true)
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 464505629765578240L;
    
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private LocalDateTime bir;

    private Integer age;


    @TableField(fill = FieldFill.INSERT_UPDATE)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    private String description;

    @TableField(typeHandler = JsonTypeHandler.class)
    private Object other;

    @TableLogic
    private int deleted;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String orgCode;

}

