package com.zj.bda.common.validator.annotation;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public @interface ValidatedRestController {
}
