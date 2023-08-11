package com.zw.cloud.tools.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 明信片
 * </p>
 *
 * @author zw
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostCard implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 公司职位
     */
    private String title;

    /**
     * 公司名称
     */
    private String org;

    /**
     * 公司电话
     */
    private String voice;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 信息
     */
    private String note;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
