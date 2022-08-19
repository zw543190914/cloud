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
            return;
        }

        String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(messageBody)) {
            log.warn("[RabbitConsumer][RabbitConsumer] msg is null");
            return;
        }

        String tag = messageExt.getTags();
        log.info("[RabbitConsumer][RabbitConsumer] tag is {},receive messageBody is {}", tag, messageBody);
        ConsumerHandler task1 = ConsumerHandler.getConsumerHandlerInstance("task1");
        task1.handleRocketMQMsg(messageBody);
    }
}
