package com.zw.cloud.rocketmq.consumer;

import com.zw.cloud.rocketmq.consumer.handler.ConsumerHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Slf4j
@RocketMQMessageListener(topic = "topicA", consumerGroup = "group1",consumeMode = ConsumeMode.ORDERLY)
public class RocketConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        if (Objects.isNull(messageExt)) {
            log.info("[RabbitConsumer][onMessage] messageExt is null");
            return;
        }

        String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(messageBody)) {
            log.warn("[RabbitConsumer][onMessage] msg is null");
            return;
        }

        String topic = messageExt.getTopic();
        String tag = messageExt.getTags();
        log.info("[RabbitConsumer][onMessage] tag is {},receive messageBody is {}", tag, messageBody);
        ConsumerHandler handlerInstance = ConsumerHandler.getConsumerHandlerInstance(topic,tag);
        if (Objects.isNull(handlerInstance)) {
            log.error("[RabbitConsumer][onMessage] tag is {},receive messageBody is {},handlerInstance is null", tag, messageBody);
            return;
        }
        handlerInstance.handleRocketMQMsg(messageBody);
    }
}
