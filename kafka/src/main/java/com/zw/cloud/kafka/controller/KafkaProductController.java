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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
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
    //http://localhost:9085/kafka/normal/gemi_device_report_dyeing_wash
    public void sendMessage1(@PathVariable("topic") String topic,@PathVariable(value = "message",required = false) String message) {
        if (StringUtils.isBlank(message)) {
            message = buildGeneralReportData(100,111, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
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

    private String buildGeneralReportData(int setValue,int actValue,Long second) {
        return "{\n" +
                "    \"sdata\":{\n" +
                "        \"generalReport\":{\n" +
                "        \"ctime\":" + second + ",\n" +
                "        \"rtime\":" + second + ",\n" +
                "        \"mt\":1,\n" +
                "        \"ec\":8830,\n" +
                "        \"e01\":" + setValue + ",\n" +
                "        \"e02\":" + actValue + ",\n" +
                "        \"e03\":" + actValue + ",\n" +
                "        \"e04\":" + setValue + ",\n" +
                "        \"e05\":" + actValue + ",\n" +
                "        \"e06\":" + setValue + ",\n" +
                "        \"e07\":" + actValue + ",\n" +
                "        \"e08\":" + setValue + ",\n" +
                "        \"e09\":" + actValue + ",\n" +
                "        \"e10\":" + setValue + ",\n" +
                "        \"e11\":" + actValue + ",\n" +
                "        \"e12\":" + setValue + ",\n" +
                "        \"e13\":" + actValue + ",\n" +
                "        \"e14\":" + setValue + ",\n" +
                "        \"e15\":" + actValue + ",\n" +
                "        \"e16\":" + setValue + ",\n" +
                "        \"e17\":" + actValue + ",\n" +
                "        \"e18\":" + setValue + ",\n" +
                "        \"e19\":" + actValue + ",\n" +
                "        \"e20\":" + setValue + ",\n" +
                "        \"e21\":" + actValue + ",\n" +
                "        \"e22\":" + setValue + ",\n" +
                "        \"e23\":" + actValue + ",\n" +
                "        \"e24\":" + setValue + ",\n" +
                "        \"e25\":" + actValue + ",\n" +
                "        \"e27\":" + actValue + ",\n" +
                "        \"e28\":" + setValue + ",\n" +
                "        \"e29\":" + actValue + ",\n" +
                "        \"e30\":" + actValue + ",\n" +
                "        \"e31\":" + setValue + ",\n" +
                "        \"e32\":" + actValue + ",\n" +
                "        \"e33\":" + setValue + ",\n" +
                "        \"e34\":" + actValue + ",\n" +
                "        \"e35\":" + setValue + ",\n" +
                "        \"e36\":" + actValue + ",\n" +
                "        \"e37\":" + setValue + ",\n" +
                "        \"e38\":" + actValue + ",\n" +
                "        \"e39\":" + setValue + ",\n" +
                "        \"e40\":" + actValue + ",\n" +
                "        \"e41\":" + setValue + ",\n" +
                "        \"e42\":" + actValue + ",\n" +
                "        \"e43\":" + setValue + ",\n" +
                "        \"e44\":" + actValue + ",\n" +
                "        \"e45\":" + setValue + ",\n" +
                "        \"e46\":" + actValue + ",\n" +
                "        \"e47\":" + setValue + ",\n" +
                "        \"e48\":" + actValue + ",\n" +
                "        \"e49\":" + setValue + ",\n" +
                "        \"e50\":" + actValue + ",\n" +
                "        \"e51\":" + setValue + ",\n" +
                "        \"e52\":" + actValue + ",\n" +
                "        \"e53\":" + setValue + ",\n" +
                "        \"e54\":" + actValue + ",\n" +
                "        \"e55\":" + actValue + ",\n" +
                "        \"e56\":" + setValue + ",\n" +
                "        \"e57\":" + actValue + ",\n" +
                "        \"e58\":" + setValue + ",\n" +
                "        \"e59\":" + actValue + ",\n" +
                "        \"e60\":" + setValue + ",\n" +
                "        \"e61\":" + actValue + ",\n" +
                "        \"e62\":" + setValue + ",\n" +
                "        \"e63\":" + actValue + ",\n" +
                "        \"e64\":" + setValue + ",\n" +
                "        \"e65\":" + actValue + "\n" +
                "        }\n" +
                "    },\n" +
                "    \"mdata\":{\n" +
                "        \"created\":true,\n" +
                "        \"device\":{\n" +
                "            \"name\":\"zw\",\n" +
                "            \"ns\":\"dyeing\"\n" +
                "        },\n" +
                "        \"labels\":{\n" +
                "            \"bindStatus\":\"1\",\n" +
                "            \"machineId\":\"1499331094789685249\",\n" +
                "            \"machineNum\":\"zw\",\n" +
                "            \"orgCode\":\"devController\"\n" +
                "        },\n" +
                "        \"link\":{\n" +
                "            \"name\":\"zw\",\n" +
                "            \"type\":\"baidu\"\n" +
                "        },\n" +
                "        \"mqtt\":{\n" +
                "            \"topic\":\"d/rr/report\"\n" +
                "        },\n" +
                "        \"msg\":{\n" +
                "            \"id\":\"1bed7280-371a-4b02-a35f-eb387bb78d7b\",\n" +
                "            \"kafkaReceiveTime\":\"2023-04-24T09:42:50.892142722Z\",\n" +
                "            \"kafkaSendTime\":\"2023-04-24T09:42:50.547Z\",\n" +
                "            \"rootTopic\":\"report\",\n" +
                "            \"subTopic\":\"\",\n" +
                "            \"topic\":\"dev_report_dyeing\"\n" +
                "        },\n" +
                "        \"receiveTime\":1682329370900\n" +
                "    }\n" +
                "}";
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
                "        \"msgId\":\"" + UUID.randomUUID().toString() + "\",\n" +
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
