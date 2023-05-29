package com.zw.cloud.common.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class LocationCodeVO implements Serializable {

    /**
     * 返回结果状态值 值为0或1,0表示失败；1表示成功
     */
    private String status;
    /**
     * 返回状态说明 返回状态说明，status为0时，info返回错误原因，否则返回“OK”。
     */
    private String info;
    /**
     * 状态码 10000代表正确
     */
    private String infocode;
    /**
     * 省份名称
     */
    private String province;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 城市的adcode编码
     */
    private String adcode;
    /**
     * 所在城市矩形区域范围
     */
    private String rectangle;
}
