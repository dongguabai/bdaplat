package com.zj.bda.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Dongguabai on 2018-06-20 14:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {

    String key();

    /**
     * 过期时间  目前使用guava、ehcache   使用redis该属性有用
     *
     */
    int expire() default 5;
}
