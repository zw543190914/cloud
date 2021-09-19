package com.zw.cloud.mybatis.plus.db.annotations;



import com.zw.cloud.mybatis.plus.db.MpCondition;

import java.lang.annotation.*;

/**
 * 字段在sql中的操作符annotation
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface MpOperator {

    /**
     * 取值见{@link MpCondition}
     */
    String value() default MpCondition.OP_EQ;

    /**
     * 当注解的字段，不是表实体类Entity的字段时，无法进行映射，要手动指定数据库column
     **/
    String columnName() default "";

}
