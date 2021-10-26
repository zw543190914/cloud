package com.zw.cloud.rabbitmq.consumer;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.zw.cloud.rabbitmq.config.MQConfig.QUEUE;

public class ConsumerTest {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //交换机声明（参数为：交换机名称；交换机类型）
        channel.exchangeDeclare(QUEUE, BuiltinExchangeType.DIRECT);
        //队列与交换机绑定（参数为：队列名称；交换机名称；routingKey）
        channel.queueBind(QUEUE,QUEUE,QUEUE);


        while(true) {
            //消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(QUEUE, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    String bodyStr = new String(body, StandardCharsets.UTF_8);
                    System.out.println(bodyStr);

                }
            });
        }
    }
}
