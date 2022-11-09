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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/rocket")
@RestController
@Slf4j
public class RocketController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    private volatile boolean run = true;

    //发送消息
    @GetMapping("/sendMsg")
    //http://localhost:10000/rocket/sendMsg?msg=aaa&topic=topicA&tag=tag1
    public void sendMsg(@RequestParam String msg,@RequestParam String topic,@RequestParam String tag) throws Exception{
        //SendResult hashkey = rocketMQTemplate.syncSendOrderly(topic, msg + " : " + atomicInteger.getAndAdd(1), "hashkey");
        topic = topic + ":" + tag;
        int count = 1;
        if (!run) {
            run = true;
        }
        while (run) {
            for (int i = 0; i < 500; i++) {

                Message<byte[]> message = MessageBuilder.withPayload((msg + count).getBytes(StandardCharsets.UTF_8)).build();
                rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("[sendMsg] send success ");
                    }
                    @Override
                    public void onException(Throwable throwable) {
                        log.info("[sendMsg] send error is ", throwable);
                    }
                });
                log.info("[sendMsg] count is {}",count);
                i ++;
                count ++;
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    //发送消息
    @GetMapping("/stopSendMsg")
    //http://localhost:10000/rocket/stopSendMsg
    public void stopSendMsg() {
        run = false;
    }

    /**
     * 发送延迟消息
     * 1  2   3   4   5  6  7  8  9 10 11 12 13 14  15  16  17 18
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    @GetMapping("/sendDelayMsg")
    //http://localhost:10000/rocket/sendDelayMsg?msg=aaa&topic=topicA&tag=tag1
    public void sendDelayMsg(@RequestParam String msg,@RequestParam String topic,@RequestParam String tag) {
        topic = topic + ":" + tag;
        Message<byte[]> message = MessageBuilder.withPayload(msg.getBytes(StandardCharsets.UTF_8)).build();
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[sendDelayMsg] send success " + sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("[sendDelayMsg] send error is " + throwable.getMessage());
            }
        },3000,4);

    }

    //发送事务消息
    @GetMapping("/sendMessageIntransaction")
    //http://localhost:10000/rocket/sendMessageIntransaction?msg=qq&topic=topicA
    public void sendMessageIntransaction(@RequestParam String msg,@RequestParam String topic) {
        Message<String> message = MessageBuilder.withPayload(msg + " : " + atomicInteger.getAndAdd(1))
               /* .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader(RocketMQHeaders.TAGS, "tag1")
                .setHeader("userId", "zw")*/
                .build();

        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(topic, message,null);
        log.info("[rabbitProduct][sendMessageIntransaction] result is {}", sendResult.toString());
    }
}
