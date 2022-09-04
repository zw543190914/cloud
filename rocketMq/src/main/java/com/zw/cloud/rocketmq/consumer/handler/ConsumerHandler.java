package com.zw.cloud.rocketmq.consumer.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.zw.cloud.rocketmq.enums.ConsumerEnum;
import org.apache.commons.lang3.StringUtils;

public abstract class ConsumerHandler {

    public static ConsumerHandler getConsumerHandlerInstance(String topic,String tag) {
        String beanName = ConsumerEnum.getBeanNameByTopicAndTag(topic,tag);
        if (StringUtils.isBlank(beanName)) {
            return null;
        }
        return SpringUtil.getBean(beanName, ConsumerHandler.class);
    }

    public abstract void handleRocketMQMsg(String messageBody);
}
