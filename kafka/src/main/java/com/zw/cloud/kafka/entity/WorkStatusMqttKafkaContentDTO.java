package com.zw.cloud.kafka.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class WorkStatusMqttKafkaContentDTO implements Serializable {

    private MData mData;

    private Object sdata;

    @Setter
    @Getter
    public static class MData implements Serializable {

        private Link link;

        private Device device;

        private Mqtt mqtt;

        private Msg msg;

    }

    @Setter
    @Getter
    public static class Link implements Serializable {

        private String type;

    }

    @Setter
    @Getter
    public static class Device implements Serializable {

        private String name;

        private String ns;
    }

    @Setter
    @Getter
    public static class Mqtt implements Serializable {

        private String topic;

    }

    @Setter
    @Getter
    public static class Msg implements Serializable {
        private String id;// gemi内部id 业务无须使用
        private String rootTopic;
        private String subTopic;
    }
}
