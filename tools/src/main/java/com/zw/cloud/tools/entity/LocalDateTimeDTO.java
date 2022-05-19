package com.zw.cloud.tools.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhengwei
 * @date 2022/5/14 16:59
 */
@Data
public class LocalDateTimeDTO implements Serializable {

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;


    private String dateStr;

}
