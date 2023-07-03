package com.zw.cloud.rocketmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/rocket")
@RestController
@Slf4j
public class RocketProductController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    private volatile boolean run = true;

    //发送消息
    @GetMapping("/sendMsg")
    //http://localhost:9095/rocket/sendMsg?msg=zw&topic=topicA&tag=tag1
    public void sendMsg(@RequestParam String msg,@RequestParam String topic,@RequestParam String tag) throws Exception{
        topic = topic + ":" + tag;
        int count = 1;
        if (!run) {
            run = true;
        }
//        while (run) {
            for (int i = 0; i < 10; i++) {

                Message<byte[]> message = MessageBuilder.withPayload((msg + count).getBytes(StandardCharsets.UTF_8)).build();
                rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("[sendMsg] send success " + sendResult);
                    }
                    @Override
                    public void onException(Throwable throwable) {
                        log.info("[sendMsg] send error is ", throwable);
                    }
                });
                //SendResult sendResult = rocketMQTemplate.syncSend(topic, message);
                //log.info("[sendMsg] send success " + sendResult);
                count ++;
            }
//            TimeUnit.SECONDS.sleep(1);
//        }
    }

    //发送消息
    @GetMapping("/sendGeneralDeviceReportData")
    //http://localhost:9095/rocket/sendGeneralDeviceReportData?topic=general_device_report_data&tag=tag1
    public void sendGeneralDeviceReportData(@RequestParam String topic,@RequestParam String tag) {
        topic = topic + ":" + tag;
        if (!run) {
            run = true;
        }
        while (run) {
            long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            String generalDeviceReportData = buildCommonContent(99, 120, second);
            Message<byte[]> message = MessageBuilder.withPayload((generalDeviceReportData).getBytes(StandardCharsets.UTF_8)).build();
            rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[sendGeneralDeviceReportData] send success " + sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    log.info("[sendGeneralDeviceReportData] send error is ", throwable);
                }
            });
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //发送消息
    @GetMapping("/stopSendMsg")
    //http://localhost:9095/rocket/stopSendMsg
    public void stopSendMsg() {
        run = false;
    }

    //发送消息
    @GetMapping("/sendOneMsg")
    //http://localhost:9095/rocket/sendOneMsg?msg=zw1&topic=topicA&tag=tag5
    public void sendOneMsg(@RequestParam String msg,@RequestParam String topic,@RequestParam String tag) {
        Message<byte[]> message = MessageBuilder.withPayload(msg.getBytes(StandardCharsets.UTF_8)).build();
        rocketMQTemplate.asyncSend( topic + ":" + tag, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[sendOneMsg] send success " + sendResult);
            }
            @Override
            public void onException(Throwable throwable) {
                log.info("[sendOneMsg] send error is ", throwable);
            }
        });
    }
    /**
     * 发送延迟消息
     * 1  2   3   4   5  6  7  8  9 10 11 12 13 14  15  16  17 18
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    @GetMapping("/sendDelayMsg")
    //http://localhost:9095/rocket/sendDelayMsg?msg=zw&topic=topicA&tag=tag1
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
        // 定时消息
        //SendResult sendResult = rocketMQTemplate.syncSendDeliverTimeMills(topic, message, System.currentTimeMillis() + 5000);

    }

    //发送事务消息
    @GetMapping("/sendMessageIntransaction")
    //http://localhost:9095/rocket/sendMessageIntransaction?msg=qq&topic=topicA
    public void sendMessageIntransaction(@RequestParam String msg,@RequestParam String topic,@RequestParam String tag) {
        Message<String> message = MessageBuilder.withPayload(msg + " : " + atomicInteger.getAndAdd(1))
               /* .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader(RocketMQHeaders.TAGS, "tag1")
                .setHeader("userId", "zw")*/
                .build();

        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(topic, message,null);
        log.info("[rabbitProduct][sendMessageIntransaction] result is {}", sendResult.toString());
    }

    private static String buildCommonContent(int setValue,int actValue,Long second){
        Random random = new Random();
        int nextInt = random.nextInt(20);
        actValue = actValue + nextInt;
        return  "{\n" +
                "    \"generalReport\":{\n" +
                "        \"ctime\":" + second + ",\n" +
                "        \"rtime\":" + second + ",\n" +
                "        \"mt\":1,\n" +
                "        \"ec\":8830,\n" +
                "        \"e01\":"+ setValue +",\n" +
                "        \"e02\":"+ actValue +",\n" +
                "        \"e03\":"+ actValue +",\n" +
                "        \"e04\":"+ setValue +",\n" +
                "        \"e05\":"+ actValue +",\n" +
                "        \"e06\":"+ setValue +",\n" +
                "        \"e07\":"+ actValue +",\n" +
                "        \"e08\":"+ setValue +",\n" +
                "        \"e09\":"+ actValue +",\n" +
                "        \"e10\":"+ setValue +",\n" +
                "        \"e11\":"+ actValue +",\n" +
                "        \"e12\":"+ setValue +",\n" +
                "        \"e13\":"+ actValue +",\n" +
                "        \"e14\":"+ setValue +",\n" +
                "        \"e15\":"+ actValue +",\n" +
                "        \"e16\":"+ setValue +",\n" +
                "        \"e17\":"+ actValue +",\n" +
                "        \"e18\":"+ setValue +",\n" +
                "        \"e19\":"+ actValue +",\n" +
                "        \"e20\":"+ setValue +",\n" +
                "        \"e21\":"+ actValue +",\n" +
                "        \"e22\":"+ setValue +",\n" +
                "        \"e23\":"+ actValue +",\n" +
                "        \"e24\":"+ setValue +",\n" +
                "        \"e25\":"+ actValue +",\n" +
                "        \"e27\":"+ actValue +",\n" +
                "        \"e28\":"+ setValue +",\n" +
                "        \"e29\":"+ actValue +",\n" +
                "        \"e30\":"+ actValue +",\n" +
                "        \"e31\":"+ setValue +",\n" +
                "        \"e32\":"+ actValue +",\n" +
                "        \"e33\":"+ setValue +",\n" +
                "        \"e34\":"+ actValue +",\n" +
                "        \"e35\":"+ setValue +",\n" +
                "        \"e36\":"+ actValue +",\n" +
                "        \"e37\":"+ setValue +",\n" +
                "        \"e38\":"+ actValue +",\n" +
                "        \"e39\":"+ setValue +",\n" +
                "        \"e40\":"+ actValue +",\n" +
                "        \"e41\":"+ setValue +",\n" +
                "        \"e42\":"+ actValue +",\n" +
                "        \"e43\":"+ setValue +",\n" +
                "        \"e44\":"+ actValue +",\n" +
                "        \"e45\":"+ setValue +",\n" +
                "        \"e46\":"+ actValue +",\n" +
                "        \"e47\":"+ setValue +",\n" +
                "        \"e48\":"+ actValue +",\n" +
                "        \"e49\":"+ setValue +",\n" +
                "        \"e50\":"+ actValue +",\n" +
                "        \"e51\":"+ setValue +",\n" +
                "        \"e52\":"+ actValue +",\n" +
                "        \"e53\":"+ setValue +",\n" +
                "        \"e54\":"+ actValue +",\n" +
                "        \"e55\":"+ actValue +",\n" +
                "        \"e56\":"+ setValue +",\n" +
                "        \"e57\":"+ actValue +",\n" +
                "        \"e58\":"+ setValue +",\n" +
                "        \"e59\":"+ actValue +",\n" +
                "        \"e60\":"+ setValue +",\n" +
                "        \"e61\":"+ actValue +",\n" +
                "        \"e62\":"+ setValue +",\n" +
                "        \"e63\":"+ actValue +",\n" +
                "        \"e64\":"+ setValue +",\n" +
                "        \"e65\":"+ actValue +"\n" +
                "    }\n" +
                "}";

    }
}
