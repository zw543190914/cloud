package com.zw.cloud.rocketmq.consumer.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.zw.cloud.rocketmq.enums.ConsumerEnum;

public abstract class ConsumerHandler {

    public static ConsumerHandler getConsumerHandlerInstance(String code) {
        String beanName = ConsumerEnum.getBeanNameByCode(code);
        return SpringUtil.getBean(beanName, ConsumerHandler.class);
    }

    public abstract void handleRocketMQMsg(String messageBody);
}
