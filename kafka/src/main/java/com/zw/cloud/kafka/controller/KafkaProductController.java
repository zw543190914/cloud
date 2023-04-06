package com.zw.cloud.kafka.controller;

import io.micrometer.core.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping(value = {"/normal/{topic}/{message}","/normal/{topic}"})
    //http://localhost:9085/kafka/normal/gemi_device_upstream_dyeing
    public void sendMessage1(@PathVariable("topic") String topic,@PathVariable(value = "message",required = false) String message) {
        if (StringUtils.isBlank(message)) {
            message = buildTestMsg();
        }
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
    }

    @GetMapping(value = {"/callbackOne/{topic}/{message}","/callbackOne/{topic}"})
    //http://localhost:9085/kafka/callbackOne/gemi_device_upstream_dyeing
    public void sendMessage2(@PathVariable("topic") String topic,@PathVariable(value = "message",required = false) String message) {
        //发送消息
        if (StringUtils.isBlank(message)) {
            message = buildTestMsg();
        }

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
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
    public void mocksSendMessage(@PathVariable("topic") String topic,@PathVariable("count") Long count) throws InterruptedException {
        int num = 1;
        while (sendMsgFlag) {
            for (int i = 0; i < count; i++) {
                sendMessage2(topic,String.valueOf(num));
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

    private String buildTestMsg() {
        return "{\n" +
                "    \"sdata\":{\n" +
                "        \"businessType\":\"work_report\",\n" +
                "        \"collectTimeS\":1680761930,\n" +
                "        \"data\":[\n" +
                "            {\n" +
                "                \"amount\":\"\",\n" +
                "                \"device\":\"\",\n" +
                "                \"deviceId\":\"\",\n" +
                "                \"deviceName\":\"\",\n" +
                "                \"finishTime\":\"2023-04-06 13:59:45\",\n" +
                "                \"horsesNumber\":\"\",\n" +
                "                \"nextProcessName\":\"Y上线\",\n" +
                "                \"operator\":\"\",\n" +
                "                \"percent\":\"\",\n" +
                "                \"person\":\"\",\n" +
                "                \"processCategory\":\"\",\n" +
                "                \"processCode\":\"Y成功\",\n" +
                "                \"processName\":\"Y成功\",\n" +
                "                \"processOrder\":\"\",\n" +
                "                \"productCardCode\":\"210509272\",\n" +
                "                \"ptQuantity\":\"\",\n" +
                "                \"ptQuantityFu\":\"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"msgId\":\"dec30415bc2f499abc9443b9b0958a28\",\n" +
                "        \"msgType\":\"work_report\",\n" +
                "        \"orgCode\":\"3983D94F788342E1B0D8F52EA890D8E8\",\n" +
                "        \"subTopic\":\"/up/stenter/erp\",\n" +
                "        \"timestamp\":1680761930,\n" +
                "        \"type\":0\n" +
                "    },\n" +
                "    \"mdata\":{\n" +
                "        \"created\":true,\n" +
                "        \"device\":{\n" +
                "            \"name\":\"dyeing-gateway-qa\",\n" +
                "            \"ns\":\"dyeing\"\n" +
                "        },\n" +
                "        \"labels\":{\n" +
                "            \"activateStatus\":\"activated\",\n" +
                "            \"activateTime\":\"2022-12-05T11:06:09.637+08:00\",\n" +
                "            \"batchId\":\"61dd4353cbfba8ea9519ddff\",\n" +
                "            \"kind\":\"gateway\",\n" +
                "            \"linkType\":\"baidu\",\n" +
                "            \"productId\":\"61dd4342cbfba8ea9519ddfe\",\n" +
                "            \"tlsEnabled\":false\n" +
                "        },\n" +
                "        \"link\":{\n" +
                "            \"name\":\"dyeing-gateway-qa\",\n" +
                "            \"type\":\"baidu\"\n" +
                "        },\n" +
                "        \"mqtt\":{\n" +
                "            \"topic\":\"d/dyeing-gateway-qa/up/stenter/erp\"\n" +
                "        },\n" +
                "        \"msg\":{\n" +
                "            \"id\":\"268ed506-5de5-4c1b-b84a-1520076aa242\",\n" +
                "            \"kafkaReceiveTime\":\"2023-04-06T06:18:50.073870636Z\",\n" +
                "            \"kafkaSendTime\":\"2023-04-06T06:18:50.017Z\",\n" +
                "            \"rootTopic\":\"upstream\",\n" +
                "            \"subTopic\":\"stenter_erp\",\n" +
                "            \"topic\":\"qa_upstream_dyeing\"\n" +
                "        },\n" +
                "        \"receiveTime\":1680761930075\n" +
                "    }\n" +
                "}";
    }
}
