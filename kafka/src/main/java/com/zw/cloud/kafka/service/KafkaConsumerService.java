package com.zw.cloud.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhengwei
 * @date 2022/3/11 20:56
 */
@Component
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = {"topic1"},groupId = "group01",id = "group1")
    public void onMessage(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("[KafkaConsumerService][onMessage] thread is {},partition is {},msg is {}",Thread.currentThread().getName(),record.partition(),record.value());
    }

    /*@KafkaListener(topics = "topic1",groupId = "group02",id = "group2")
    public void onMessage2(List<ConsumerRecord<?, ?>> records) {
        log.info("[KafkaConsumerService][onMessage2] thread is {}, 批量消费一次，records.size is {}",Thread.currentThread().getName(),records.size());
        for (ConsumerRecord<?, ?> record : records) {
            log.info("[KafkaConsumerService][onMessage2] thread is {},partition is {},msg is {}",Thread.currentThread().getName(),record.partition(),record.value());
        }
    }*/

}
