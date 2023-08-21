package com.zw.cloud.influxdb.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 通用生产记录工艺关联表
 * </p>
 *
 * @author zw
 * @since 2022-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class GeneralProductRecordPlanCraft implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 通用生产记录id(general_product_record)
     */
    private Long generalProductRecordId;

    /**
     * 机构编号
     */
    private String orgCode;

    /**
     * 温度
     */
    private BigDecimal temperature;

    /**
     * 车速
     */
    private BigDecimal speed;

    /**
     * 碱浓度
     */
    private BigDecimal alkaliConcentration;

    /**
     * 保温时间
     */
    private BigDecimal holdingTime;

    /**
     * 减量率
     */
    private BigDecimal decrementRate;

    /**
     * 克重差
     */
    private BigDecimal gramWeightDiff;

    /**
     * PH值
     */
    private BigDecimal ph;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否已经删除，0未删除,1已删除
     */
    private Integer isDeleted;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建系统
     */
    private String createSystem;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 修改系统
     */
    private String updateSystem;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 水槽张力
     */
    private Integer tension;

    /**
     * 出布轧车压力(0-100)
     */
    private Integer rollingPressure;

    /**
     * 真空频率(0-100)
     */
    private BigDecimal vacuumFrequency;

    /**
     * 助剂
     */
    private Object assistant;

    /**
     * 温度JSON
     */
    private Object temperatureJson;

    /**
     * 水槽张力JSON
     */
    private Object tensionJson;

    /**
     * 出布张力 0-100闭区间，单位“N”
     */
    private BigDecimal fabricTension;

    /**
     * 进布张力 0-100闭区间，单位“N”
     */
    private BigDecimal feedTension;

    /**
     * 进布轧车压力(0-100)
     */
    private Integer feedRollingPressure;

    /**
     * 轧车压力
     */
    private BigDecimal carTension;

    /**
     * 轧车压力JSON
     */
    private Object carTensionJson;

    /**
     * ph值JSON
     */
    private Object phJson;

    /**
     * 真空吸水/真空频率JSON
     */
    private Object vacuumFrequencyJson;

    /**
     * 补水量
     */
    private BigDecimal supplyWaterQuantity;

    /**
     * 补水量JSON
     */
    private Object supplyWaterQuantityJson;

    /**
     * 含水量
     */
    private BigDecimal waterContent;

    /**
     * 含水量JSON
     */
    private Object waterContentJson;

    /**
     * 助剂配比
     */
    private BigDecimal assistantRate;

    /**
     * 助剂配比JSON
     */
    private Object assistantRateJson;

    /**
     * 判断是否有工艺数据
     */
    public boolean checkHasCraft() {
        Class<GeneralProductRecordPlanCraft> planCraftDTOClass = GeneralProductRecordPlanCraft.class;
        Field[] declaredFields = planCraftDTOClass.getDeclaredFields();
        boolean hasCraft = false;
        HashSet<String> notNeedCheckFieldNameSet = Sets.newHashSet("id","generalProductRecordId","orgCode",
                "isDeleted","createUser","createSystem","createTime","updateUser","updateSystem","updateTime","log","serialVersionUID");
        HashSet<String> jsonFieldNameSet = Sets.newHashSet("temperatureJson","tensionJson","carTensionJson",
                "phJson","vacuumFrequencyJson","supplyWaterQuantityJson","waterContentJson","assistantRateJson");
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (notNeedCheckFieldNameSet.contains(field.getName())) {
                continue;
            }
            try {
                Object value = field.get(this);
                if (Objects.isNull(value)) {
                    continue;
                }
                if (StringUtils.equalsIgnoreCase("assistant",field.getName())) {
                    List<JSONObject> assistantDTOList = JSON.parseArray(JSON.toJSONString(value), JSONObject.class);
                    if (CollectionUtils.isNotEmpty(assistantDTOList)) {
                        return true;
                    }
                    continue;
                }
                if (jsonFieldNameSet.contains(field.getName())) {
                    Map valueMap = JSON.parseObject(JSON.toJSONString(value), Map.class);
                    if (MapUtils.isNotEmpty(valueMap)) {
                        return true;
                    }
                    continue;
                }
                if (value instanceof String) {
                    if (StringUtils.isNotBlank((String)value)) {
                        return true;
                    }
                } else {
                    return true;
                }

            } catch (IllegalAccessException e) {
                log.warn("[GeneralProductRecordPlanCraftCheck][checkHasCraft]field.getName is {}, error is ",field.getName(),e);
            }
        }
        return hasCraft;
    }

    public static void main(String[] args) {
        GeneralProductRecordPlanCraft generalProductRecordPlanCraft = new GeneralProductRecordPlanCraft();
        generalProductRecordPlanCraft.setId(1L);
        System.out.println(generalProductRecordPlanCraft.checkHasCraft());
        generalProductRecordPlanCraft.setAssistant(Lists.newArrayList(new GeneralProductRecordPlanCraft()));
        System.out.println(generalProductRecordPlanCraft.checkHasCraft());
    }
}
