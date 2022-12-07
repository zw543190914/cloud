package com.zw.cloud.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class LocationVO implements Serializable {
    private static final long serialVersionUID = 549335373784935893L;

    /**
     * 城市编码
     */
    private String citycode;
    /**
     * 区域编码
     */
    private String adcode;
    /**
     * 行政区名称
     */
    private String name;
    /**
     * 区域中心点
     */
    private String center;
    /**
     * 行政区划级别
     */
    private String level;
    /**
     * 下级行政区列表
     */
    private List<?> districts;
}
