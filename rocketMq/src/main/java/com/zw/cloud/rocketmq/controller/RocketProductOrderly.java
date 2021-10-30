package com.zw.cloud.rocketmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            SendResult result = rocketMQTemplate.syncSendOrderly(topic, message, "hashKey");
            //log.info("send success {}" ,JSONUtil.toJsonStr(result));
        }
    }

}
