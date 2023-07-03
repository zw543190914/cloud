package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class ImageInfo implements Serializable {

    /**
     * 图片高度
     */
    private String imageHeight;
    /**
     * 图片宽度
     */
    private String imageWidth;
    /**
     * 拍摄时间
     */
    private LocalDateTime createTime;
    /**
     * 纬度
     */
    private String imageLatitude;
    /**
     * 经度
     */
    private String imageLongitude;
    /**
     * 地址
     */
    private String address;
}
