package com.zw.cloud.rocketmq.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * topic 枚举
 */
public enum ConsumerEnum {
    /**
     * 通用设备上报数据
     */
    GENERAL_DEVICE_REPORT_DATA( "general_device_report_data","tag1", "通用设备上报数据","generalDeviceReportData"),
    /**
     * 顺序消费
     */
    ORDERLY_TASK_CONSUMER("topicB","tag1", "任务2","orderlyConsumerHandlerInstance");


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
