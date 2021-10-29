package com.zw.cloud.rocketmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/rocket")
@RestController
@Slf4j
public class RocketController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    //发送消息
    @GetMapping("/sendMsg")
    //http://localhost:10000/rocket/sendMsg?msg=aaa&topic=topicA
    public void sendMessage(@RequestParam String msg,@RequestParam String topic) {
        //SendResult hashkey = rocketMQTemplate.syncSendOrderly(topic, msg + " : " + atomicInteger.getAndAdd(1), "hashkey");
        topic = topic +":"+"tag1";
        Message<byte[]> message = MessageBuilder.withPayload(msg.getBytes(StandardCharsets.UTF_8)).build();
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("------------asyncSend: " + sendResult);
            }
            @Override
            public void onException(Throwable throwable) {
                log.info("------------asyncSend: " + throwable.getMessage());
            }
        });
    }

    //发送事务消息
    @GetMapping("/sendMessageIntransaction")
    //http://localhost:10000/rocket/sendMessageIntransaction?msg=&topic=
    public void sendMessageIntransaction(@RequestParam String msg,@RequestParam String topic) {
        Message<String> message = MessageBuilder.withPayload(msg + " : " + atomicInteger.getAndAdd(1))
                .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader(RocketMQHeaders.TAGS, "tag1")
                .setHeader("userId", "zw")
                .build();
        String destination = topic;
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, destination);
        log.info("[rabbitProduct][sendMessageIntransaction] result is {}", sendResult.toString());
    }
}
