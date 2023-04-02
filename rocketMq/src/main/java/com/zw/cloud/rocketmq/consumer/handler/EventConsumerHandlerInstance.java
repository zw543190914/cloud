package com.zw.cloud.rocketmq.consumer.handler;

import com.zw.cloud.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("eventConsumerHandlerInstance")
@Slf4j
public class EventConsumerHandlerInstance extends ConsumerHandler{

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[EventConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}",messageBody);
        // 有异常，阻塞后续消费
        /*if (StringUtils.equals("zw5",messageBody)) {
            throw new BizException("ex");
        }*/
        log.info("[EventConsumerHandlerInstance][handleRocketMQMsg] messageBody is {},end ",messageBody);

    }
}
