package com.zw.cloud.tools.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
        int qos = 1;
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();
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
        //long second = 1635659894;
        int stop = 0;
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        for (int i = 0; i < 120; i++) {
            int j = i + 88;
            second = second + i;
            if (i%2 == 1) {
                stop = 0;
            } else {
                stop = 3;
            }
            String content = buildContent(j,stop,second);
            try {

                // 创建消息
                MqttMessage message = new MqttMessage(content.getBytes());
                // 设置消息的服务质量
                message.setQos(qos);
                // 发布消息
                sampleClient.publish(topic, message);

            } catch (MqttException me) {
                System.out.println("reason " + me.getReasonCode());
                System.out.println("msg " + me.getMessage());
                System.out.println("loc " + me.getLocalizedMessage());
                System.out.println("cause " + me.getCause());
                System.out.println("excep " + me);
                me.printStackTrace();
                throw me;
            }
            System.out.println("finish " + i);
            //System.out.println(content);
            Thread.sleep(500);
        }
        // 断开连接
        sampleClient.disconnect();
        // 关闭客户端
        sampleClient.close();

    }

    private static String buildContent(int j,int stop,Long second){
        String content = "{\n" +
                "\"stenterStatus\":{\n" +
                "    \"rtime\": "+ second +",     \n" +
                "    \"ctime\": "+ second +",    \n" +
                "    \"mt\": 1,\n" +
                "    \"ec\": 8830,\n" +
                "    \"d01\": "+ stop +",\n" +
                "    \"d02\": "+ stop +",\n" +
                "    \"d03\": " + j +",\n" +
                "    \"d04\": " + j +",\n" +
                "    \"d05\": " + j +",\n" +
                "    \"d06\": " + j +",\n" +
                "    \"d07\": " + j +",\n" +
                "    \"d08\": " + j +",\n" +
                "    \"d09\": " + j +",\n" +
                "    \"d10\": " + j +",\n" +
                "    \"d11\": " + j +",\n" +
                "    \"d12\": " + j +",\n" +
                "    \"d13\": " + j +",\n" +
                "    \"d14\": " + j +",\n" +
                "    \"d15\": " + j +",\n" +
                "    \"d16\": " + j +",\n" +
                "    \"d17\": " + j +",\n" +
                "    \"d18\": " + j +",\n" +
                "    \"d19\": " + j +",\n" +
                "    \"d20\": " + j +",\n" +
                "    \"d21\": " + j +",\n" +
                "    \"d22\": " + j +",\n" +
                "    \"d23\": " + j +",\n" +
                "    \"d24\": " + j +",\n" +
                "    \"d25\": " + j +",\n" +
                "    \"d26\": " + j +",\n" +
                "    \"d27\": " + j +",\n" +
                "    \"d28\": " + j +",\n" +
                "    \"d29\": " + j +",\n" +
                "    \"d30\": " + j +",\n" +
                "    \"d31\": " + j +",\n" +
                "    \"d32\": " + j +",\n" +
                "    \"d33\": " + j +",\n" +
                "    \"d34\": " + j +",\n" +
                "    \"d35\": " + j +",\n" +
                "    \"d36\": " + j +",\n" +
                "    \"d37\": " + j +",\n" +
                "    \"d38\": " + j +",\n" +
                "    \"d39\": " + j +",\n" +
                "    \"d40\": " + j +",\n" +
                "    \"d41\": " + j +",\n" +
                "    \"d42\": " + j +",\n" +
                "    \"d43\": " + j +",\n" +
                "    \"d44\": " + j +",\n" +
                "    \"d45\": " + j +",\n" +
                "    \"d46\": " + j +",\n" +
                "    \"d47\": " + j +",\n" +
                "    \"d48\": " + j +",\n" +
                "    \"d49\": " + j +",\n" +
                "    \"d50\": " + j +",\n" +
                "    \"d51\": " + j +",\n" +
                "    \"d52\": " + j +",\n" +
                "    \"d53\": " + j +",\n" +
                "    \"d54\": " + j +",\n" +
                "    \"d55\": " + j +",\n" +
                "    \"d56\": " + j +",\n" +
                "    \"d57\": " + j +",\n" +
                "    \"d58\": " + j +",\n" +
                "    \"d59\": " + j +",\n" +
                "    \"d60\": " + j +",\n" +
                "    \"d61\": " + j +",\n" +
                "    \"d65\": " + j +",\n" +
                "    \"d66\": " + j +",\n" +
                "    \"d67\": " + j +",\n" +
                "    \"d68\": " + j +",\n" +
                "    \"d69\": " + j +",\n" +
                "    \"d70\": " + j +",\n" +
                "    \"d71\": " + j +",\n" +
                "    \"d72\": " + j +" \n" +
                "  }\n" +
                "}";
        return content;
    }
}
