package com.zw.cloud.tools.controller.event;

import com.zw.cloud.tools.event.entity.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/event")
@RestController
@Slf4j
public class EventController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/publishEvent/{businessId}")
    //http://localhost:9040/event/publishEvent/001
    public void publishEvent(@PathVariable String businessId) {
        log.info("[EventController][publishEvent] businessId is {}",businessId);
        publisher.publishEvent(BaseEvent.builder().businessId(businessId).build());
    }
}
