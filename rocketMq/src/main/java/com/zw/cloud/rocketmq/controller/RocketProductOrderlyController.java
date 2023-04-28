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

@RequestMapping("/rocket/orderly")
@RestController
@Slf4j
public class RocketProductOrderlyController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 顺序消息
     * consumeMode = ConsumeMode.ORDERLY
     */
    @GetMapping("/syncSendOrderly")
    //http://localhost:9095/rocket/orderly/syncSendOrderly?topic=topicB&tag=tag1&msg=zw
    public void syncSendOrderly(@RequestParam String msg,@RequestParam String topic,@RequestParam String tag) {
        for (int i = 0; i < 10; i++) {
            Message<byte[]> message = MessageBuilder.withPayload((msg + atomicInteger.getAndAdd(1)).getBytes(StandardCharsets.UTF_8)).build();
            //SendResult result = rocketMQTemplate.syncSendOrderly(topic, message, "hashKey");
            //log.info("send success {}" ,JSONUtil.toJsonStr(result));
            // 异步发送,未按顺序发送
            String hashKey = "msg";

            /*rocketMQTemplate.asyncSendOrderly(topic + ":" + tag, message, hashKey, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[syncSendOrderly] send success " + sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    log.info("[syncSendOrderly] send error is " + throwable.getMessage());
                }
            });*/
            // 同步发送 保证顺序
            SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic + ":" + tag, message, hashKey);
            log.info("[syncSendOrderly] send success " + sendResult);

        }
    }

    @GetMapping("/testStrategy/{topic}/{tag}")
    //http://localhost:9095/rocket/orderly/testStrategy/topic
    public void testStrategy(@PathVariable String topic,@PathVariable String tag) {
        ConsumerHandler task1 = ConsumerHandler.getConsumerHandlerInstance(topic,tag);
        task1.handleRocketMQMsg(tag);
    }

}
