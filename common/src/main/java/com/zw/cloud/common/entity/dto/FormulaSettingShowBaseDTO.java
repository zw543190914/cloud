package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 预配详情页-工厂&个人展示设置
 * @author: zpj
 * @date:  2023-03-21 15:10
 */
@Getter
@Setter
public class FormulaSettingShowBaseDTO implements Serializable {


    /**
     * 来样信息
     */
    private List<FormulaSettingShowValueDTO> sampleInfo;

    /**
     * 颜色信息
     */
    private List<FormulaSettingShowValueDTO> colorInfo;

    /**
     * 打样信息
     */
    private List<FormulaSettingShowValueDTO>  samplePrintInfo;

    /**
     * 推荐组合/修色
     */
    private List<FormulaSettingShowValueDTO>  recommendGroup;

    /**
     * 自选组合
     */
    private List<FormulaSettingShowValueDTO>  choose;

    /**
     * 结果展示
     */
    private List<FormulaSettingShowValueDTO>  result;

    /**
     * 来样是否展示
     */
    private Integer sampleShow;

    

}
