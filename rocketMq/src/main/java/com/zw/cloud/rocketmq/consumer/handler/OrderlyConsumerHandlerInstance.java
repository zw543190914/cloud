package com.zw.cloud.rocketmq.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("orderlyConsumerHandlerInstance")
@Slf4j
public class OrderlyConsumerHandlerInstance extends ConsumerHandler{

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[OrderlyConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}",messageBody);
        // 同步顺序发送，使用orderly可以顺序消费 有异常，maxReconsumeTimes不设置，阻塞后续消费,不断重试
        // 异步发送无法，使用orderly无法保证顺序消息
        /*if (StringUtils.equals("zw5",messageBody)) {
            throw new BizException("ex");
        }*/

    }
}
