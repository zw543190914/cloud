package com.zw.cloud.tools.entity;

import com.zw.cloud.common.enums.DesensitizationTypeEnum;
import com.zw.cloud.common.annotation.Desensitization;
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
    @Desensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String name;

    /**
     * 手机号
     */
    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String tel;

    /**
     * 公司职位
     */
    @Desensitization(type = DesensitizationTypeEnum.MY_RULE,startInclude = 2,endExclude = 4)
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
    @Desensitization(type = DesensitizationTypeEnum.ADDRESS)
    private String address;

    /**
     * 邮箱
     */
    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
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
