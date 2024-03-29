package com.zw.cloud.tools.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MqttConfig {
    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.url}")
    private String hostUrl;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    /**
     * 客户端对象
     */
    private MqttClient client;

    @Autowired
    MqttCallBackService mqttCallBackService;

    /**
     * 在bean初始化后连接到服务器
     */
    /*@PostConstruct
    public void init(){
        connect();
    }*/

    /**
     * 客户端连接服务端
     */
    public void connect(){
        try{
            //创建MQTT客户端对象
            client = new MqttClient(hostUrl,clientId,new MemoryPersistence());

            //连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            //是否清空session，设置false表示服务器会保留客户端的连接记录（订阅主题，qos）,客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
            //设置为true表示每次连接服务器都是以新的身份
            options.setCleanSession(false);
            //设置连接用户名
            options.setUserName(username);
            //设置连接密码
            options.setPassword(password.toCharArray());
            //设置超时时间，单位为秒
            options.setConnectionTimeout(30);
            //设置心跳时间 单位为秒，表示服务器每隔 1.5*20秒的时间向客户端发送心跳判断客户端是否在线
            options.setKeepAliveInterval(20);
            //设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
            options.setWill("willTopic",(clientId + "与服务器断开连接").getBytes(),0,false);
            //设置回调
            client.setCallback(mqttCallBackService);
            client.connect(options);

            //订阅主题
            //消息等级，和主题数组一一对应，服务端将按照指定等级给订阅了主题的客户端推送消息
            int[] qos = {1};
            //主题
            String[] topics = {topic};
            //订阅主题
            client.subscribe(topics,qos);
            log.info("[Mqtt][connect] success");
        } catch(MqttException e){
            e.printStackTrace();
            log.error("[Mqtt][connect] error ",e);
        }

    }

    public void replayConnect () throws Exception{
        log.warn("断开连接，建议重连");
        //断开连接，重连
        int tryTimes = 1;
        while (!client.isConnected()) {
            Thread.sleep(2000);
            log.info("重试第{}次", tryTimes);
            this.connect();
            log.info("连接完成");
            tryTimes++;

        }

    }

    public void publish(String message) throws Exception{
        MqttMessage mqttMessage = new MqttMessage();
        // QoS 1 最少一次
        mqttMessage.setQos(1);
        // 重新连接MQTT服务时，不需要接收该主题最新消息，设置retained为false;
        mqttMessage.setRetained(false);
        mqttMessage.setPayload(message.getBytes());
        //主题的目的地，用于发布/订阅信息
        MqttTopic mqttTopic = client.getTopic(topic);
        //提供一种机制来跟踪消息的传递进度
        //用于在以非阻塞方式（在后台运行）执行发布是跟踪消息的传递进度
        MqttDeliveryToken token;
        try {
            //将指定消息发布到主题，但不等待消息传递完成，返回的token可用于跟踪消息的传递状态
            //一旦此方法干净地返回，消息就已被客户端接受发布，当连接可用，将在后台完成消息传递。
            token = mqttTopic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (Exception e) {
            log.error("[Mqtt][publish]topic is {},message is {},error is ",topic,message,e);
            throw e;
        }
    }

    /**
     * 订阅主题
     */
    /*public void subscribe(String topic,int qos){
        try {
            client.subscribe(topic,qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }*/

}

