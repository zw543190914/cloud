package com.zw.cloud.common.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class WeatherDTO implements Serializable {

    private List<ResultsDTO> results;

    @NoArgsConstructor
    @Data
    public static class ResultsDTO {
        private LocationDTO location;
        private List<DailyDTO> daily;
        /**
         * 数据更新时间（该城市的本地时间）
         */
        private String last_update;

        @NoArgsConstructor
        @Data
        public static class LocationDTO {
            private String id;
            private String name;
            private String country;
            private String path;
            /**
             * Asia/Shanghai
             */
            private String timezone;
            /**
             * +08:00
             */
            private String timezone_offset;
        }

        @NoArgsConstructor
        @Data
        public static class DailyDTO {

            private String date;
            /**
             * 白天天气现象文字
             */
            private String text_day;
            /**
             * 天气现象代码
             */
            private String code_day;
            /**
             * 晚间天气现象文字
             */
            private String text_night;
            private String code_night;
            /**
             * 当天最高温度
             */
            private String high;
            /**
             * 当天最低温度
             */
            private String low;
            /**
             * 降水量，单位mm
             */
            private String rainfall;
            /**
             * 降水概率 范围0~100
             */
            private String precip;
            /**
             * 风向文字
             */
            private String wind_direction;
            /**
             * 风向角度，范围0~360
             */
            private String wind_direction_degree;
            /**
             * 风速，单位km/h（当unit=c时）、mph（当unit=f时）
             */
            private String wind_speed;
            /**
             * 风力等级
             */
            private String wind_scale;
            /**
             * 相对湿度
             */
            private String humidity;
        }
    }
}
