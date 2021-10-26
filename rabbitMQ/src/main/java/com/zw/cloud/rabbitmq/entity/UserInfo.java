package com.zw.cloud.rabbitmq.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 464505629765578240L;
    
    private Long id;

    private String name;

    private LocalDateTime bir;

    private Integer age;

    private LocalDateTime updateTime;

    private String description;

}

