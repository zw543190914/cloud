package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @Description: 预配详情页-工厂&个人展示设置
 * @author: zpj
 * @date:  2023-03-21 15:10
 */
@Getter
@Setter
public class FormulaSettingShowValueDTO implements Serializable {

    /**
     *
     */
    private String key;

    /**
     *
     */
    private String value;


    /**
     * 选择与否
     */
    private Integer  selected;
    

}
