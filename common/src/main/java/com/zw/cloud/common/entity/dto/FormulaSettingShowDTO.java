package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @Description: 预配详情页-工厂&个人展示设置
 * @author: zpj
 * @date:  2023-03-21 15:10
 */
@Getter
@Setter
public class FormulaSettingShowDTO {



    /**
     * 保存设置的人
     */
    private String uacUserId;


    /**
     * 版本
     */
    private Integer version;

    /**
     * 配置信息json串
     */
    private FormulaSettingShowBaseDTO configJson;


    /**
     * 工厂id
     */
    private String orgCode;


    /**
     * 创建用户
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新用户
     */
     private String updateUserId;

    /**
     * 更新时间
     */
     private LocalDateTime updateTime;
    

}
