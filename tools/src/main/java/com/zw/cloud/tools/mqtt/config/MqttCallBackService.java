package com.zw.cloud.tools.mqtt.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2022/5/25 13:20
 */
@Slf4j
@Component
public class MqttCallBackService implements MqttCallback {

    @Autowired
    MqttConfig mqttConfig;

    /**
     * 与服务器断开的回调
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.error("[Mqtt][connectionLost]与服务器断开连接");
        try {
            mqttConfig.replayConnect();
        } catch (Exception e) {
            log.error("[Mqtt][connectionLost]replayConnect error is ",e);
        }
    }

    /**
     * 消息到达的回调
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("[Mqtt][messageArrived]topic {},message is  {}",topic, JSON.toJSONString(message));

        System.out.println(String.format("接收消息主题 : %s",topic));
        System.out.println(String.format("接收消息Qos : %d",message.getQos()));
        System.out.println(String.format("接收消息内容 : %s",new String(message.getPayload())));
        System.out.println(String.format("接收消息retained : %b",message.isRetained()));

    }

    /**
     * 消息发布成功的回调
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        IMqttAsyncClient client = token.getClient();
        int messageId = token.getMessageId();
        log.info("[Mqtt][deliveryComplete]client is {}, messageId is {} 发布消息成功",client.getClientId(),messageId);
    }

}
