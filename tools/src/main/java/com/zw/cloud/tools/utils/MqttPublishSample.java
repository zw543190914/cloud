package com.zw.cloud.tools.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *发布端
 */
public class MqttPublishSample {
    public static void main(String[] args) throws Exception{

        String host = "tcp://axdkagn.iot.gz.baidubce.com";
        String userName = "thingidp@axdkagn|dev_test_device_stenter_03|0|MD5";
        String password = "a1b48d70a8d106b861f58c1c32bdb04a";
        String topic = "d/dev_test_device_stenter_03/report";
        String clientId = "subscribe_test_device";

        String content = "{ \"stenterStatus\" :{ \"rtime\" : 1624252075,\n" +
                " \"ctime\" : 1624252075,\n" +
                " \"mt\" : 1,\n" +
                " \"ec\" : 1200,\n" +
                " \"d01\" : 0,\n" +
                " \"d02\" : 0,\n" +
                " \"d03\" : 2000,\n" +
                " \"d04\" : 2000,\n" +
                " \"d05\" : 2000,\n" +
                " \"d06\" : 2000,\n" +
                " \"d07\" : 2000,\n" +
                " \"d08\" : 2000 } }";
        int qos = 1;
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(host, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(content.getBytes());
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            sampleClient.publish(topic, message);
            // 断开连接
            sampleClient.disconnect();
            // 关闭客户端
            sampleClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
