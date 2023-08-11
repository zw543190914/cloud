package com.zw.cloud.tools.entity.poem;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Poem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类别
     */
    private String type;

    /**
     * 创建人
     */
    private String createUser;


}
