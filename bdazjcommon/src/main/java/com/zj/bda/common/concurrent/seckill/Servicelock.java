package com.zj.bda.common.concurrent.seckill;

import java.lang.annotation.*;

/**
 * @author Dongguabai
 * @date 2018/8/5 11:59
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Servicelock {
    String description() default "";
}
