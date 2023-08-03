package com.zw.cloud.kafka.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zw.cloud.kafka.entity.ProductRecord;
import com.zw.cloud.kafka.entity.WorkStatusMqttKafkaContentDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author zhengwei
 * @date 2022/3/11 20:56
 */
@Component
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = {"topic1","gemi_device_upstream_dyeing"},groupId = "group01",idIsGroup = false)
    public void onMessage(ConsumerRecord<?, ?> record) throws InterruptedException {
        // 消费的哪个topic、partition的消息,打印出消息内容
        Object value = record.value();
        log.info("[KafkaConsumerService][onMessage] thread is {},topic is {},partition is {},msg is {}",Thread.currentThread().getName(),record.topic(),record.partition(), value);
        if (Objects.isNull(value)) {
            return;
        }
        String message = value.toString();
        WorkStatusMqttKafkaContentDTO mqttKafkaContentDTO = JSON.parseObject(message, WorkStatusMqttKafkaContentDTO.class);
        if (Objects.isNull(mqttKafkaContentDTO.getSdata())) {
            log.warn("[KafkaConsumerService][onMessage] received message sdata attribute is empty!");
            return;
        }
        // 兼容新旧两种消息格式
        WorkStatusMqttKafkaContentDTO.Msg msg = mqttKafkaContentDTO.getMData().getMsg();
        String subTopic;
        if (Objects.nonNull(msg) && StringUtils.isNotEmpty(msg.getSubTopic())) {
            subTopic = "/up/" + msg.getSubTopic().replace("_", "/");
        } else {
            subTopic = mqttKafkaContentDTO.getMData().getMqtt().getTopic();
        }
        //基本参数校验
        String sData = JSON.toJSONString(mqttKafkaContentDTO.getSdata());
        JSONObject sdataObject = JSON.parseObject(sData);
        String orgCode = sdataObject.getString("orgCode");
        //工厂ID
        if (StringUtils.isBlank(orgCode)) {
            log.warn("[KafkaConsumerService][onMessage] orgCode is empty,skip execution!");
            return;
        }

        //消息内容
        String data = sdataObject.getString("data");
        if (StringUtils.isBlank(data)) {
            log.warn("[KafkaConsumerService][onMessage] data subject is empty,skip execution!");
            return;
        }
        //业务类型
        String businessType = sdataObject.getString("businessType");
        if (StringUtils.isBlank(businessType)) {
            log.warn("[KafkaConsumerService][onMessage] businessType is empty,skip execution!");
            return;
        }

        String msgId = sdataObject.getString("msgId");
        log.info("[KafkaConsumerService][onMessage] subTopic is {},businessType is {},msgId is {}",subTopic,businessType,msgId);

    }

    /*@KafkaListener(topics = "topic1",groupId = "group01",id = "group1")
    public void onMessage2(List<ConsumerRecord<?, ?>> records) throws InterruptedException {
        log.info("[KafkaConsumerService][onMessage2] thread is {}, 批量消费一次，records.size is {}",Thread.currentThread().getName(),records.size());
        for (ConsumerRecord<?, ?> record : records) {
            log.info("[KafkaConsumerService][onMessage2] thread is {},partition is {},msg is {}",Thread.currentThread().getName(),record.partition(),record.value());
            TimeUnit.SECONDS.sleep(1);
        }
    }*/

    private ProductRecord buildProductRecord(){
        String s = "{\n" +
                "    \"id\":\"1473468562871898114\",\n" +
                "    \"productInfoId\":\"1473468660754370561\",\n" +
                "    \"orgCode\":\"devController\",\n" +
                "    \"deviceId\":\"1470590869131509761\",\n" +
                "    \"deviceName\":\"test_定型机#cqk\",\n" +
                "    \"productCardCode\":\"190508224\",\n" +
                "    \"customerNo\":\"N0066\",\n" +
                "    \"customerName\":\"王志伟\",\n" +
                "    \"colorNo\":\"19050698\",\n" +
                "    \"color\":\"68#黑色\",\n" +
                "    \"fabricNo\":\"PB250601\",\n" +
                "    \"fabricName\":\"0601#丝棉\",\n" +
                "    \"batchNo\":\"904170005\",\n" +
                "    \"fabricWidth\":\"150\",\n" +
                "    \"gramWeight\":72,\n" +
                "    \"gramHeft\":8,\n" +
                "    \"weftDensity\":8,\n" +
                "    \"planMeters\":1369,\n" +
                "    \"defect\":[\n" +
                "\n" +
                "    ],\n" +
                "    \"severity\":\"一般\",\n" +
                "    \"result\":\"放行\",\n" +
                "    \"carNumber\":\"8\",\n" +
                "    \"upperDoorWidth\":9,\n" +
                "    \"lowerDoorWidth\":9,\n" +
                "    \"preStatus\":\"2\",\n" +

                "    \"preSort\":\"2\",\n" +
                "    \"afterStatus\":\"1\",\n" +
                "    \"handleTime\":\"2021-12-22 09:53:06\",\n" +
                "    \"saveTime\":\"2021-12-22 09:53:06\",\n" +
                "    \"operator\":\"测试121\",\n" +
                "    \"sort\":0,\n" +
                "    \"source\":1,\n" +
                "    \"createTypeName\":\"排产\",\n" +
                "    \"createTime\":\"2021-12-22 09:40:55\",\n" +
                "    \"createUser\":\"6FE65050C36C45B28DE3EEF866471EF6\",\n" +
                "    \"createSystem\":\"B4263529337148489E88A215BE562CF8\",\n" +
                "    \"updateTime\":\"2021-12-22 09:53:06\",\n" +
                "    \"updateUser\":\"6FE65050C36C45B28DE3EEF866471EF6\",\n" +
                "    \"updateSystem\":\"B4263529337148489E88A215BE562CF8\",\n" +
                "    \"isDeleted\":0,\n" +
                "    \"productSort\":\"0\",\n" +
                "    \"employeeName\":\"业务员名称\",\n" +
                "    \"assistant\":[\n" +
                "        {\n" +
                "            \"id\":\"83\",\n" +
                "            \"name\":\"无氟防水剂CWR-8B\",\n" +
                "            \"value\":8,\n" +
                "            \"parentId\":\"74\",\n" +
                "            \"parentName\":\"防水类\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"79\",\n" +
                "            \"name\":\"防水剂880\",\n" +
                "            \"value\":4,\n" +
                "            \"parentId\":\"74\",\n" +
                "            \"parentName\":\"防水类\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"startTime\":\"2021-12-22 09:41:19\",\n" +
                "    \"endTime\":\"2021-12-22 09:51:29\",\n" +
                "    \"workshopId\":\"1466599063445401602\",\n" +
                "    \"calcDay\":\"2021-12-21\",\n" +
                "    \"craftType\":\"293\",\n" +
                "    \"craftTypeName\":\"成定\",\n" +
                "    \"preCarNumber\":\"7\",\n" +
                "    \"specification\":\"\",\n" +
                "    \"style\":\"\",\n" +
                "    \"matches\":\"\",\n" +
                "    \"actualMeters\":1253\n" +
                "}";
        return JSON.parseObject(s, ProductRecord.class);
    }
}
