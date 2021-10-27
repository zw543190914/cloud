package com.zw.cloud.rabbit.consumer.service;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {

    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchage";


    @RabbitListener(queues = QUEUE)
    public void directReceive(Message msg, Channel channel) throws IOException {
        try {
            System.out.println("directReceive msg is " + new String(msg.getBody()));
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false );
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("directReceive receiver fail");
            e.printStackTrace();
        }

    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TOPIC_QUEUE1, durable = "true"),
            exchange = @Exchange(name =TOPIC_EXCHANGE,
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "topic.#"))
    public void topicReceive1(Message message, Channel channel) throws IOException {
        try {
            System.out.println("topicReceive1 user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            System.out.println("topicReceive1 receiver fail");
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = TOPIC_QUEUE2)
    public void topicReceive2(Message message, Channel channel) throws IOException {
        try {
            System.out.println("topicReceive2 user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false );
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("topicReceive2 receiver fail");
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = FANOUT_QUEUE2)
    public void fanoutReceive1(Message message, Channel channel) throws IOException {
        try {
            System.out.println("fanoutReceive2 user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false );
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("fanoutReceive2 receiver fail");
            e.printStackTrace();
        }
    }
}
