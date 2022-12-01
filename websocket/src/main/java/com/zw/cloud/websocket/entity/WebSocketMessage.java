package com.zw.cloud.websocket.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebSocketMessage implements Serializable {
    private String msg;
    private String targetId;
    private String currentId;
}
