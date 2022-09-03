package com.zw.cloud.tools.longpolling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class NoticeEvent extends ApplicationEvent {

    private final String deviceId;

    private final String changeTag;

    public NoticeEvent(String deviceId,String changeTag) {
        super(deviceId);
        this.deviceId = deviceId;
        this.changeTag = changeTag;
    }

    public String getChangeTag() {
        return changeTag;
    }

    public String getDeviceId() {
        return deviceId;
    }
}

