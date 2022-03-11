package com.zw.cloud.kafka.controller;

import com.google.common.collect.Lists;
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


    @GetMapping("/normal/{message}")
    //http://localhost:9080/kafka/normal/msg
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }

    @GetMapping("/callbackOne/{message}")
    //http://localhost:9080/kafka/callbackOne/msg02
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

}
