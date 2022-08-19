package com.zw.cloud.rocketmq.controller;

import com.zw.cloud.rocketmq.consumer.handler.ConsumerHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/rocket")
@RestController
@Slf4j
public class RocketProductOrderly {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 顺序消息
     * consumeMode = ConsumeMode.ORDERLY
     */
    @GetMapping("/syncSendOrderly")
    //http://localhost:10000/rocket/syncSendOrderly?topic=topicA:tag1
    public void syncSendOrderly(@RequestParam String topic) {
        for (int i = 0; i < 100; i++) {
            String msg = String.valueOf(atomicInteger.getAndAdd(1));
            Message<byte[]> message = MessageBuilder.withPayload(msg.getBytes(StandardCharsets.UTF_8)).build();
            //SendResult result = rocketMQTemplate.syncSendOrderly(topic, message, "hashKey");
            //log.info("send success {}" ,JSONUtil.toJsonStr(result));
            // 异步发送
            rocketMQTemplate.asyncSendOrderly(topic, message, "hashKey", new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    //log.info("send success " + sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    //log.info("send error is " + throwable.getMessage());
                }
            });
        }
    }

    @GetMapping("/testStrategy/{code}")
    //http://localhost:10000/rocket/testStrategy/task1
    public void testStrategy(@PathVariable String code) {
        ConsumerHandler task1 = ConsumerHandler.getConsumerHandlerInstance(code);
        task1.handleRocketMQMsg(code);
    }

}
