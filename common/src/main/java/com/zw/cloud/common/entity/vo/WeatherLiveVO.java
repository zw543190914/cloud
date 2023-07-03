package com.zw.cloud.common.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class WeatherLiveVO implements Serializable {

    /**
     * 值为0或1
     * 1：成功；0：失败
     */
    private String status;
    /**
     * 返回结果总数目
     */
    private String count;
    private String info;
    /**
     * 返回状态说明,10000代表正确
     */
    private String infocode;
    private List<ForecastsDTO> lives;

    @NoArgsConstructor
    @Data
    public static class ForecastsDTO {
        private String city;
        /**
         * 区域编码
         */
        private String adcode;
        private String province;
        /**
         * 数据发布的时间
         */
        private String reporttime;

        /**
         * 实时天气
         */
        private String weather;

        /**
         * 实时气温
         */
        private String temperature;

        /**
         * 风向
         */
        private String winddirection;

        /**
         * 风力级别
         */
        private String windpower;

        /**
         * 空气湿度
         */
        private String humidity;

        private String daytempFloat;
        private String nighttempFloat;
    }
}
