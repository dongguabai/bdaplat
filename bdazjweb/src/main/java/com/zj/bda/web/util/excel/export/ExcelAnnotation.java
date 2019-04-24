package com.zj.bda.web.util.excel.export;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
    /**
     * Excel列ID
     */
    int id();
    /**
     * Excel列名
     */
    String name();

    /**
     * Excel列宽
     */
    int width();
    /**
     * 垂直方向合并单元格方法名称,调用该方法,结果应返回Integer类型的值
     */
    String merge() default "";

}
