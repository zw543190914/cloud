package com.zw.cloud.tools.event.subscribe;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.event.entity.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventSubscribeService {

    @EventListener
    public void eventSubscribe(BaseEvent baseEvent) {
        log.info("[EventSubscribeService][eventSubscribe] eventSubscribe is {}", JSON.toJSONString(baseEvent));
    }
}
