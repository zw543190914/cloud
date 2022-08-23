package com.zw.cloud.rocketmq.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("eventConsumerHandlerInstance")
@Slf4j
public class EventConsumerHandlerInstance extends ConsumerHandler{

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[EventConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}",messageBody);
    }
}
