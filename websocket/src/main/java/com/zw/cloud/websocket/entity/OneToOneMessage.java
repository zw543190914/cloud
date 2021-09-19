package com.zw.cloud.websocket.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OneToOneMessage implements Serializable {
    private String msg;
    private String name;
    private String userId;
}
