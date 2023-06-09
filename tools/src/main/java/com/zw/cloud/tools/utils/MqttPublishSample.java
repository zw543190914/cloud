package com.zw.cloud.tools.utils;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MqttPublishSample {
    public static void main(String[] args) throws Exception{

        // dev
        // 18hao设备5
        String host = "tcp://axdkagn.iot.gz.baidubce.com";
        String userName = "thingidp@axdkagn|rr|0|MD5";
        String password = "20f582bfab251a639a8893d13d90bdc1";
        String topic = "d/rr/report";
        String clientId = "subscribe_test_device01";
        boolean isStenter = true;

       /* // test_定型机#cqk
        String host = "tcp://axdkagn.iot.gz.baidubce.com";
        String userName = "thingidp@axdkagn|cqk_zy|0|MD5";
        String password = "668eeb0a7fa18dab53a805b317b45976";
        String topic = "d/cqk_zy/report";
        String clientId = "subscribe_test_device01";
        boolean isStenter = true;*/

        // 其他工厂 dev 大栋  321
        /*String host = "tcp://axdkagn.iot.gz.baidubce.com";
        String userName = "thingidp@axdkagn|1211|0|MD5";
        String password = "5e0b273cdf52f75570b3710574af3ea0";
        String topic = "d/1211/report";
        String clientId = "subscribe_test";
        boolean isStenter = true;*/

        // qa
        // 定型机#02
        /*String host = "tcp://amgjjzk.iot.gz.baidubce.com";
        String userName = "thingidp@amgjjzk|qa_test_device_stenter_02|0|MD5";
        String password = "3509803995f1748b7d4c5f1ad9dfb615";
        String topic = "d/qa_test_device_stenter_02/report";
        String clientId = "subscribe_test";
        boolean isStenter = true;*/


        // 定型机2
        /*String host = "tcp://amgjjzk.iot.gz.baidubce.com";
        String userName = "thingidp@amgjjzk|8888|0|MD5";
        String password = "bdf6a12cb4d85d36954a9759e24b12d4";
        String topic = "d/8888/report";
        String clientId = "subscribe_test001";
        boolean isStenter = true;*/

        // 线上
        /*String host = "tcp://afswjqe.iot.gz.baidubce.com";
        String userName = "thingidp@afswjqe|xnbh06|0|MD5";
        String password = "885c1c1ba9c32c96f2c0e4f5dd0b4e01";
        String topic = "d/xnbh06/report";
        String clientId = "subscribe_test";
        boolean isStenter = true;*/

        // qa通用设备 device1651719933  2342701
       /* String host = "tcp://amgjjzk.iot.gz.baidubce.com";
        String userName = "thingidp@amgjjzk|2342701|0|MD5";
        String password = "25249998d919796775bb61fbc1559fe7";
        String topic = "d/2342701/report";
        String clientId = "subscribe_test_device01";
        boolean isStenter = false;*/

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
        int stop = 7;
        int actValue = 75 ;
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));

        for (int i = 0; i < 44640; i++) {

            second = second + 30;
            String content;
            if (isStenter) {
                content = buildContent(181,actValue ,130.4,180.56,100 ,stop,second);
            } else {
                content = buildCommonContent(99,120,second);
            }

            try {

                // 创建消息
                MqttMessage message = new MqttMessage(content.getBytes());
                // 设置消息的服务质量
                message.setQos(qos);
                // 发布消息
                sampleClient.publish(topic, message);

            } catch (MqttException me) {
                log.error("sampleClient.publish error is ",me);
            }
            log.info("sampleClient.publish finish {}, i is {}",second,i);
            TimeUnit.SECONDS.sleep(30);
        }
        // 断开连接
        sampleClient.disconnect();
        // 关闭客户端
        sampleClient.close();

    }

    public static String buildContent(int setValue,int actValue,double actValue01,double actValue02,double actValue03,int speed,Long second){
        Random random = new Random();
        int nextInt = random.nextInt(20);
        actValue = actValue + nextInt;
        return "{\n" +
                "\"stenterStatus\":{\n" +
                "    \"rtime\": "+ second +",     \n" +
                "    \"ctime\": "+ second +",    \n" +
                "    \"mt\": 1,\n" +
                "    \"ec\": 8830,\n" +
                "    \"d01\": "+ speed +",\n" +
                "    \"d02\": "+ speed +",\n" +
                "    \"d04\": " + actValue +",\n" +
                "    \"d05\": " + actValue +",\n" +
                "    \"d03\": " + actValue +",\n" +
                "    \"d06\": " + setValue +",\n" +
                "    \"d07\": " + actValue01 +",\n" +
                "    \"d08\": " + setValue +",\n" +
                "    \"d09\": " + actValue02 +",\n" +
                "    \"d10\": " + setValue +",\n" +
                "    \"d11\": " + actValue03 +",\n" +
                "    \"d12\": " + setValue +",\n" +
                //"    \"d13\": " + actValue +",\n" +
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
                // d24	totalAmplitude 总门幅
                "    \"d24\": " + 12 +",\n" +
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
                // 排风设定转速百分比1
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
                // 循环风设定转速百分比12
                "    \"d101\": " + 80 +", \n" +
                // 协议版本号（固定为1.6）
                "    \"d150\": " + 1.6 +",\n" +
                "    \"d400\": " + setValue +",\n" +
                "    \"d401\": " + actValue +",\n" +
                "    \"d500\": " + actValue +",\n" +
                "    \"d501\": " + actValue +",\n" +
                "    \"d502\": " + actValue +",\n" +
                "    \"d600\": " + actValue +",\n" +
                "    \"d601\": " + actValue +",\n" +
                "    \"d602\": " + actValue +",\n" +
                "    \"d603\": " + actValue +",\n" +
                "    \"d604\": " + setValue +",\n" +
                // 后门幅实际
                "    \"d605\": " + actValue +",\n" +
                "    \"d606\": " + setValue +",\n" +
                // 门幅1实际
                "    \"d607\": " + actValue +",\n" +
                "    \"d608\": " + setValue +",\n" +
                "    \"d609\": " + actValue +",\n" +
                "    \"d610\": " + setValue +",\n" +
                "    \"d611\": " + actValue +",\n" +
                "    \"d612\": " + setValue +",\n" +
                "    \"d613\": " + actValue +",\n" +
                "    \"d614\": " + setValue +",\n" +
                "    \"d615\": " + actValue +",\n" +
                "    \"d616\": " + setValue +",\n" +
                "    \"d617\": " + actValue +",\n" +
                "    \"d618\": " + setValue +",\n" +
                "    \"d619\": " + actValue +",\n" +
                "    \"d620\": " + setValue +",\n" +
                "    \"d621\": " + actValue +",\n" +
                "    \"d622\": " + setValue +",\n" +
                "    \"d623\": " + actValue +",\n" +
                "    \"d624\": " + setValue +",\n" +
                "    \"d625\": " + actValue +",\n" +
                "    \"d626\": " + setValue +",\n" +
                // 门幅11实际
                "    \"d627\": " + 120 +",\n" +
                "    \"d628\": " + setValue +",\n" +
                // 门幅12实际
                "    \"d629\": " + 110 +",\n" +
                "    \"d700\": " + actValue +",\n" +
                "    \"d701\": " + actValue +",\n" +
                "    \"d702\": " + actValue +",\n" +
                "    \"d703\": " + actValue +",\n" +
                "    \"d704\": " + actValue +",\n" +
                "    \"d705\": " + actValue +",\n" +
                "    \"d706\": " + actValue +",\n" +
                "    \"d707\": " + actValue +",\n" +
                "    \"d708\": " + actValue +"\n" +
                "  }\n" +
                "}";
    }

    public static String buildCommonContent(int setValue,int actValue,Long second){
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
