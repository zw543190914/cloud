package com.zw.cloud.rabbitmq.product;

import cn.hutool.json.JSONUtil;
import com.zw.cloud.rabbitmq.entity.UserInfo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.zw.cloud.rabbitmq.config.MQConfig.*;

@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQController implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    AtomicLong atomicInteger = new AtomicLong(1);

    @RequestMapping("/sendDirector")
    //http://localhost:9090/rabbitMQ/sendDirector
    public void sendDirector(){
        UserInfo user = new UserInfo();
        user.setId(atomicInteger.getAndAdd(1));
        user.setAge(12);
        user.setUpdateTime(LocalDateTime.now());
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(QUEUE, JSONUtil.toJsonStr(user).getBytes(),correlationData);
    }

    @RequestMapping("/sendTopic1")
    //http://localhost:9090/rabbitMQ/sendTopic1
    public void sendTopic1(){
        //消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        UserInfo user = new UserInfo();
        user.setId(atomicInteger.getAndAdd(1));
        user.setName("topic1");
        user.setBir(LocalDateTime.now());
        //routingKey = topic.queue1
        CorrelationData correlationData = new CorrelationData("001");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,"topic.c",JSONUtil.toJsonStr(user).getBytes(),correlationData);
    }

    @RequestMapping("/sendTopic2")
    //http://localhost:9090/rabbitMQ/sendTopic2
    public void sendTopic2(){
        //消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        UserInfo user = new UserInfo();
        user.setId(atomicInteger.getAndAdd(1));
        user.setName("topic2");
        user.setBir(LocalDateTime.now());
        //routingKey = topic.queue1
        CorrelationData correlationData = new CorrelationData("002");

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,TOPIC_QUEUE2,JSONUtil.toJsonStr(user).getBytes(),correlationData);
    }

    @RequestMapping("/sendFanout1")
    //http://localhost:9090/rabbitMQ/sendFanout1
    public void sendFanout(){
        UserInfo user = new UserInfo();
        user.setId(atomicInteger.getAndAdd(1));
        user.setName("fanout1");
        user.setBir(LocalDateTime.now());
        // 参数 2 忽略
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE,FANOUT_QUEUE1,JSONUtil.toJsonStr(user).getBytes());
    }

    //延时发送
    //http://localhost:9090/rabbitMQ/sendDelay
    @RequestMapping("/sendDelay")
    public void sendDelay(){
        UserInfo user = new UserInfo();
        user.setId(atomicInteger.getAndAdd(1));
        user.setName("delay");
        user.setBir(LocalDateTime.now());
        //第一个参数是前面RabbitMqConfig的交换机名称 第二个参数的路由名称 第三个参数是传递的参数 第四个参数是配置属性
        this.rabbitTemplate.convertAndSend(
                DELAY_EXCHANGE,
                "delay.key",
                JSONUtil.toJsonStr(user).getBytes(),
                message -> {
                    //配置消息的过期时间
                    message.getMessageProperties().setExpiration("5000");
                    return message;
                }
        );

    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //System.out.println("callbakck confirm: " + correlationData.getId());
        if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }

}
