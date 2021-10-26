package com.zw.cloud.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.zw.cloud.rabbitmq.config.MQConfig.QUEUE;
import static com.zw.cloud.rabbitmq.config.MQConfig.TOPIC_QUEUE1;

@Service
public class ConsumerService {

    @RabbitListener(queues = QUEUE)
    public void directReceive(Message message, Channel channel) {

        try {
            System.out.println("directReceive user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false );
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

   /* @RabbitListener(queues = "topic.#")
    public void topicReceive2(Message message){
        System.out.println("topicReceive2 user is " + new String(message.getBody()));
    }
*/
}
