package com.zw.cloud.tools.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *发布端
 */
public class MqttPublishSample {
    public static void main(String[] args) throws Exception{

        // dev
        // 18hao设备5
        String host = "tcp://axdkagn.iot.gz.baidubce.com";
        String userName = "thingidp@axdkagn|rr|0|MD5";
        String password = "20f582bfab251a639a8893d13d90bdc1";
        String topic = "stenter";
        String clientId = "subscribe_test_device01";
        // qa
        // 定型机#02
       /* String host = "tcp://amgjjzk.iot.gz.baidubce.com";
        String userName = "thingidp@amgjjzk|qa_test_device_stenter_02|0|MD5";
        String password = "3509803995f1748b7d4c5f1ad9dfb615";
        String topic = "d/qa_test_device_stenter_02/report";
        String clientId = "subscribe_test";*/
        // 线上
        /*String host = "tcp://afswjqe.iot.gz.baidubce.com";
        String userName = "thingidp@afswjqe|xnbh06|0|MD5";
        String password = "885c1c1ba9c32c96f2c0e4f5dd0b4e01";
        String topic = "d/xnbh06/report";
        String clientId = "subscribe_test";*/

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
        //long second = 1658383250;
        int stop;
        Random random = new Random();
        int actValue = 113 ;
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        for (int i = 0; i < 120; i++) {

            second = second + 30;
            if (i == 2) {
                stop = 33;
                //actValue = 189;
            } else {
                stop = 100;
                //actValue = 178;
            }
            actValue =  actValue + 50;
            String content = buildContent(180,actValue,stop,second);
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
            System.out.println("finish " + second);
            //System.out.println(content);
            TimeUnit.SECONDS.sleep(30);
        }
        // 断开连接
        sampleClient.disconnect();
        // 关闭客户端
        sampleClient.close();

    }

    private static String buildContent(int setValue,int actValue,int speed,Long second){
        String content = "{\n" +
                "\"stenterStatus\":{\n" +
                "    \"rtime\": "+ second +",     \n" +
                "    \"ctime\": "+ second +",    \n" +
                "    \"mt\": 1,\n" +
                "    \"ec\": 8830,\n" +
                "    \"d01\": "+ setValue +",\n" +
                "    \"d02\": "+ speed +",\n" +
                "    \"d04\": " + actValue +",\n" +
                "    \"d05\": " + actValue +",\n" +
                "    \"d03\": " + actValue +",\n" +
                "    \"d06\": " + setValue +",\n" +
                "    \"d07\": " + actValue +",\n" +
                "    \"d08\": " + setValue +",\n" +
                "    \"d09\": " + actValue +",\n" +
                "    \"d10\": " + setValue +",\n" +
                "    \"d11\": " + actValue +",\n" +
                "    \"d12\": " + setValue +",\n" +
                "    \"d13\": " + actValue +",\n" +
                "    \"d14\": " + setValue +",\n" +
                "    \"d15\": " + actValue +",\n" +
                "    \"d16\": " + setValue +",\n" +
                "    \"d17\": " + actValue +",\n" +
                "    \"d18\": " + setValue +",\n" +
                "    \"d19\": " + actValue +",\n" +
                "    \"d20\": " + setValue +",\n" +
                "    \"d21\": " + actValue +",\n" +
                "    \"d22\": " + setValue +",\n" +
                "    \"d23\": " + actValue +",\n" +
                "    \"d24\": " + actValue +",\n" +
                "    \"d25\": " + actValue +",\n" +
                "    \"d26\": " + actValue +",\n" +
                "    \"d27\": " + actValue +",\n" +
                "    \"d28\": " + actValue +",\n" +
                "    \"d29\": " + actValue +",\n" +
                "    \"d30\": " + actValue +",\n" +
                "    \"d31\": " + actValue +",\n" +
                "    \"d32\": " + actValue +",\n" +
                "    \"d33\": " + actValue +",\n" +
                "    \"d34\": " + setValue +",\n" +
                "    \"d35\": " + actValue +",\n" +
                "    \"d36\": " + setValue +",\n" +
                "    \"d37\": " + actValue +",\n" +
                "    \"d38\": " + setValue +",\n" +
                "    \"d39\": " + actValue +",\n" +
                "    \"d40\": " + setValue +",\n" +
                "    \"d41\": " + actValue +",\n" +
                "    \"d42\": " + setValue +",\n" +
                "    \"d43\": " + actValue +",\n" +
                "    \"d44\": " + setValue +",\n" +
                "    \"d45\": " + actValue +",\n" +
                "    \"d46\": " + setValue +",\n" +
                "    \"d47\": " + actValue +",\n" +
                "    \"d48\": " + setValue +",\n" +
                "    \"d49\": " + actValue +",\n" +
                "    \"d50\": " + setValue +",\n" +
                "    \"d51\": " + actValue +",\n" +
                "    \"d52\": " + setValue +",\n" +
                "    \"d53\": " + actValue +",\n" +
                "    \"d54\": " + setValue +",\n" +
                "    \"d55\": " + actValue +",\n" +
                "    \"d56\": " + setValue +",\n" +
                "    \"d57\": " + actValue +",\n" +
                "    \"d58\": " + setValue +",\n" +
                "    \"d59\": " + actValue +",\n" +
                "    \"d60\": " + actValue +",\n" +
                "    \"d61\": " + actValue +",\n" +
                "    \"d65\": " + setValue +",\n" +
                "    \"d66\": " + actValue +",\n" +
                "    \"d67\": " + setValue +",\n" +
                "    \"d68\": " + actValue +",\n" +
                "    \"d69\": " + setValue +",\n" +
                "    \"d70\": " + actValue +",\n" +
                "    \"d71\": " + setValue +",\n" +
                "    \"d72\": " + actValue +",\n" +
                "    \"d73\": " + setValue +",\n" +
                "    \"d74\": " + actValue +",\n" +
                "    \"d75\": " + setValue +",\n" +
                "    \"d76\": " + actValue +",\n" +
                "    \"d77\": " + setValue +",\n" +
                "    \"d78\": " + actValue +",\n" +
                "    \"d79\": " + setValue +",\n" +
                "    \"d80\": " + actValue +",\n" +
                "    \"d81\": " + setValue +",\n" +
                "    \"d82\": " + actValue +",\n" +
                "    \"d83\": " + 80 +",\n" +
                "    \"d84\": " + 80 +",\n" +
                "    \"d85\": " + 80 +",\n" +
                "    \"d86\": " + 80 +",\n" +
                "    \"d87\": " + 80 +",\n" +
                "    \"d88\": " + 80 +",\n" +
                "    \"d89\": " + 80 +",\n" +
                "    \"d90\": " + 80 +",\n" +
                "    \"d91\": " + 80 +",\n" +
                "    \"d92\": " + 80 +",\n" +
                "    \"d93\": " + 80 +",\n" +
                "    \"d94\": " + 80 +",\n" +
                "    \"d95\": " + 80 +",\n" +
                "    \"d96\": " + 80 +",\n" +
                "    \"d97\": " + 80 +",\n" +
                "    \"d98\": " + 80 +",\n" +
                "    \"d99\": " + 80 +",\n" +
                "    \"d100\": " + 80 +",\n" +
                "    \"d101\": " + 80 +" \n" +
                "  }\n" +
                "}";
        return content;
    }
}
