package com.zw.cloud.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.zw.cloud.rabbitmq.config.MQConfig.*;

@Service
public class ConsumerService {

    @RabbitListener(queues = QUEUE)
    public void directReceive(Message message) throws IOException {
        System.out.println("directReceive user is " + new String(message.getBody()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TOPIC_QUEUE2, durable = "true"),
            exchange = @Exchange(name =TOPIC_EXCHANGE,
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "topic.#"))
    public void topicReceive(Message message, Channel channel) throws IOException {
        try {
            System.out.println("topicReceive1 user is " + new String(message.getBody()));
            //throw new RuntimeException("exception...");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("topicReceive1 receiver fail");
            throw e;
        }
    }

    @RabbitListener(queues = TOPIC_QUEUE2)
    public void topicReceive2(Message message, Channel channel) throws IOException {
        try {
            System.out.println("topicReceive2 user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("topicReceive2 receiver fail");
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = FANOUT_QUEUE1)
    public void fanoutReceive1(Message message, Channel channel) throws IOException {
        try {
            System.out.println("fanoutReceive1 user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("fanoutReceive1 receiver fail");
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = FANOUT_QUEUE2)
    public void fanoutReceive2(Message message, Channel channel) throws IOException {
        try {
            System.out.println("fanoutReceive2 user is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("fanoutReceive2 receiver fail");
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "delay.data.receive_queue")
    public void delayDataReceive(Message message,Channel channel) throws IOException {
        try {
            System.out.println("delay.data is " + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            //费者处理出了问题，需要告诉队列信息消费失败
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
    }

}
