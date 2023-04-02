package com.zw.cloud.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.rocketmq.consumer.handler.ConsumerHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.support.RocketMQConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Slf4j
@RocketMQMessageListener(topic = "topicA", consumerGroup = "group2",maxReconsumeTimes = 3,
        selectorExpression = "tag4 || tag5",consumeMode = ConsumeMode.ORDERLY)
public class RocketOrderlyConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("[RocketOrderlyConsumer][onMessage] thread is {},messageExt is {}", Thread.currentThread().getName() ,JSON.toJSONString(messageExt));
        if (Objects.isNull(messageExt)) {
            log.info("[RocketOrderlyConsumer][onMessage] messageExt is null");
            return;
        }

        String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(messageBody)) {
            log.warn("[RocketOrderlyConsumer][onMessage] msg is null");
            return;
        }

        String topic = messageExt.getTopic();
        String tag = messageExt.getTags();
        log.info("[RocketOrderlyConsumer][onMessage] tag is {},receive messageBody is {}", tag, messageBody);
        ConsumerHandler handlerInstance = ConsumerHandler.getConsumerHandlerInstance(topic,tag);
        if (Objects.isNull(handlerInstance)) {
            log.warn("[RocketOrderlyConsumer][onMessage] tag is {},receive messageBody is {},handlerInstance is null", tag, messageBody);
            return;
        }
        handlerInstance.handleRocketMQMsg(messageBody);
    }

}
