package com.zw.cloud.rabbitmq.product;

import cn.hutool.json.JSONUtil;
import com.zw.cloud.rabbitmq.entity.UserInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.zw.cloud.rabbitmq.config.MQConfig.*;

@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQController implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendDirector")
    //http://localhost:9090/rabbitMQ/sendDirector
    public void sendDirector(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this::returnedMessage);
        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setAge(12);
        user.setUpdateTime(LocalDateTime.now());
        rabbitTemplate.convertAndSend(QUEUE, JSONUtil.toJsonStr(user).getBytes());
    }

    @RequestMapping("/sendTopic")
    //http://localhost:9090/rabbitMQ/sendTopic
    public void sendTopic(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this::returnedMessage);
        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setName("topic1");
        user.setBir(LocalDateTime.now());
        //routingKey = topic.queue1
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,TOPIC_QUEUE1,JSONUtil.toJsonStr(user).getBytes());

        UserInfo user2 = new UserInfo();
        user2.setId(1L);
        user2.setName("topic2");
        user2.setBir(LocalDateTime.now());
        //routingKey = topic.queue1
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,TOPIC_QUEUE2,JSONUtil.toJsonStr(user).getBytes());
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("callbakck confirm: " + correlationData.getId());
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message.getMessageProperties().getCorrelationId() + " 发送失败");
    }
}
