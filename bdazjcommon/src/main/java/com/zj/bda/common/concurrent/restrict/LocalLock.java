package com.zj.bda.common.concurrent.restrict;

import java.lang.annotation.*;

/**
 * Created by Dongguabai on 2018-06-20 14:53
 * 分布式下不可用，不能乱用，否则会出问题，主要是过滤连续表单提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {


    String key();

    boolean keyGenerate() default true;

    /**
     * 过期时间  目前使用guava、ehcache，该属性无作用   使用redis该属性有用
     *
     */
    int expire() default 5;
}
