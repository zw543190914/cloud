package com.zw.cloud.rocketmq.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * topic 枚举
 */
public enum ConsumerEnum {
    /**
     * 事件
     */
    EVENT_TASK_CONSUMER("task1", "TOPIC1","tag1", "任务1","eventConsumerHandlerInstance"),
    /**
     * 变更
     */
    CHANGE_TASK_CONSUMER("task2","TOPIC2","tag1", "任务2","changeConsumerHandlerInstance");

    /**
     * code
     */
    private final String code;

    /**
     * topic
     */
    private final String topic;
    /**
     * tag
     */
    private final String tag;

    /**
     * 描述
     */
    private final String description;

    /**
     * bean名称
     */
    private final String beanName;

    ConsumerEnum(String code, String topic, String tag, String description,String beanName) {
        this.code = code;
        this.topic = topic;
        this.tag = tag;
        this.description = description;
        this.beanName = beanName;
    }

    public static String getBeanNameByCode(String code){
        for (ConsumerEnum value : ConsumerEnum.values()) {
            if (StringUtils.equals(value.code,code)) {
                return value.beanName;
            }
        }
        return null;
    }

}
