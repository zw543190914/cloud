package com.zw.cloud.rabbit.consumer.service;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {

    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";

    @RabbitListener(queues = QUEUE)
    public void directReceive(Message msg, Channel channel){
        try {
            System.out.println("directReceive msg is " + new String(msg.getBody()));
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false );
        } catch (IOException e) {
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("directReceive receiver fail");
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = TOPIC_QUEUE1)
    public void topicReceive(Message message){
        System.out.println("topicReceive user is " + new String(message.getBody()));
    }
}
