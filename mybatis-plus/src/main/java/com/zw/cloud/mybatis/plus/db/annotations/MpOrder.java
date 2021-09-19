package com.zw.cloud.mybatis.plus.db.annotations;

import com.zw.cloud.mybatis.plus.db.MpCondition;

import java.lang.annotation.*;

/**
 * @author relam
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface MpOrder {

    /**
     * 取值见{@link MpCondition#ORDER_ASC}
     *
     * @return 排序方向
     */
    String order() default MpCondition.ORDER_ASC;

    /**
     * 控制字段顺序, 值小的在order by前面
     *
     * @return 位置数值
     */
    int seq() default 0;

    /**
     * 当注解的字段，不是表实体类Entity的字段时，无法进行映射，要手动指定数据库column
     *
     * @return 数据库表column
     */
    String columnName() default "";

}
