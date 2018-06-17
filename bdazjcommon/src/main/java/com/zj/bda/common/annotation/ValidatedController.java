package com.zj.bda.common.annotation;


import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Dongguabai on 2018-06-18.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Validated
@Controller
public @interface ValidatedController {
}
