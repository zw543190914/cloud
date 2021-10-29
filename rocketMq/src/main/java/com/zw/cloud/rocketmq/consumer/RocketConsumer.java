package com.zw.cloud.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
@RocketMQMessageListener(topic = "topicA", consumerGroup = "group1",
        // 设置为顺序消费
        consumeMode = ConsumeMode.ORDERLY )
public class RocketConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        String tag = messageExt.getTags();
        log.info("[RabbitConsumer][RabbitConsumer] tag is {},receive messageBody is {}",tag, messageBody);
        if (StringUtils.isEmpty(messageBody)) {
            log.warn("[RabbitConsumer][RabbitConsumer] msg is null");
            return;
        }
        if (messageBody.contains("3")) {
            throw new RuntimeException("ex...");
        }
    }

    /*@Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeOrderlyStatus.SUCCESS;
        }
        //设置自动提交
        context.setAutoCommit(true);
        msgs.forEach(msg -> {

            String messageBody = new String(msg.getBody(), StandardCharsets.UTF_8);
            log.info("[RabbitConsumer][RabbitConsumer] Handle Order Message: messageId: " + msg.getMsgId() + ",topic: " + msg.getTopic() + ",tags: "
                    + msg.getTags() + ",keys: " + msg.getKeys() + ",messageBody: " + messageBody);
        });
        return ConsumeOrderlyStatus.SUCCESS;
    }*/
}
