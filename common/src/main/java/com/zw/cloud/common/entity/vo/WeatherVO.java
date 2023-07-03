package com.zw.cloud.common.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class WeatherVO implements Serializable {

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
    private List<ForecastsDTO> forecasts;

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
        private List<CastsDTO> casts;

        @NoArgsConstructor
        @Data
        public static class CastsDTO {
            private String date;
            /**
             * 星期几
             */
            private String week;
            /**
             * 白天天气现象
             */
            private String dayweather;
            /**
             * 晚上天气现象
             */
            private String nightweather;
            /**
             * 白天温度
             */
            private String daytemp;
            /**
             * 晚上温度
             */
            private String nighttemp;
            /**
             * 白天风向
             */
            private String daywind;
            /**
             * 晚上风向
             */
            private String nightwind;
            /**
             * 白天风力
             */
            private String daypower;
            /**
             * 晚上风力
             */
            private String nightpower;
            private String daytempFloat;
            private String nighttempFloat;
        }
    }
}
