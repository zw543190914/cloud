package com.zw.cloud.rocketmq.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * topic 枚举
 */
public enum ConsumerEnum {
    /**
     * 事件
     */
    EVENT_TASK_CONSUMER( "topicA","tag1", "任务1","eventConsumerHandlerInstance"),
    /**
     * 变更
     */
    CHANGE_TASK_CONSUMER("topicB","tag1", "任务2","changeConsumerHandlerInstance");


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

    ConsumerEnum(String topic, String tag, String description,String beanName) {
        this.topic = topic;
        this.tag = tag;
        this.description = description;
        this.beanName = beanName;
    }

    public static String getBeanNameByTopicAndTag(String topic,String tag){
        for (ConsumerEnum value : ConsumerEnum.values()) {
            if (StringUtils.equals(value.topic,topic) && StringUtils.equals(value.tag,tag) ) {
                return value.beanName;
            }
        }
        return null;
    }

}
