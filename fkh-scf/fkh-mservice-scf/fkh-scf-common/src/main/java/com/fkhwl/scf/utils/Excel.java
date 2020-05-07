package com.fkhwl.scf.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by: dong4j
 * Date: 2017-07-26
 * Time: 11:18
 * Description: excel 导出类注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface Excel {
    // 导出到Excel中的列名
    String name();
    // 是否需要转换, 默认不需要, 如果需要根据类型转换成不同输出, 则标识为 true
    boolean convert() default false;
    // 针对一个实体多种导出类型的情况
    String[] owner() default {};
}
