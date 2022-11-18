package com.zw.cloud.kafka.controller;

import io.micrometer.core.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengwei
 * @date 2022/3/11 20:56
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaProductController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    volatile boolean sendMsgFlag = true;

    @GetMapping("/normal/{message}")
    //http://localhost:9085/kafka/normal/msg
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic1", normalMessage);
    }

    @GetMapping("/callbackOne/{message}")
    //http://localhost:9085/kafka/callbackOne/msg02
    public void sendMessage2(@PathVariable("message") String obj) {
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic1", obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(@NonNull Throwable throwable) {
                //发送失败的处理
                log.info("生产者发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info("生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }

    @GetMapping("mocksSendMessage/{count}")
    //http://localhost:9085/kafka/mocksSendMessage/10000
    public void mocksSendMessage(@PathVariable("count") Long count) throws InterruptedException {
        int num = 1;
        while (sendMsgFlag) {
            for (int i = 0; i < count; i++) {
                sendMessage2(String.valueOf(num));
                num ++;
            }
            TimeUnit.SECONDS.sleep(2);
        }

    }

    @GetMapping("updateSendMsgFlag/{sendMsgFlag}")
    //http://localhost:9085/kafka/updateSendMsgFlag/false
    public void updateSendMsgFlag(@PathVariable("sendMsgFlag") boolean sendMsgFlag) {
        this.sendMsgFlag = sendMsgFlag;
    }

}
