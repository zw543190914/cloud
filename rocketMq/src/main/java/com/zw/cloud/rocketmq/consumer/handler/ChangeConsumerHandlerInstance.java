package com.zw.cloud.rocketmq.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("changeConsumerHandlerInstance")
@Slf4j
public class ChangeConsumerHandlerInstance extends ConsumerHandler{

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[ChangeConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}",messageBody);
    }
}
