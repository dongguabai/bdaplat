package com.zj.bda.common.validator.annotation;


import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dongguabai
 * @date 2018-06-30 1:02
 *@RequestMapping("/validation")
 *@RestController
 *@Validated
 *    public class ValidationController {
 *    如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。
 *   @RequestMapping(value = "/demo3", method = RequestMethod.GET)
 *   public void demo3(@Range(min = 1, max = 9, message = "年级只能从1-9")
    @RequestParam(name = "grade", required = true)
                          int grade,
    @Min(value = 1, message = "班级最小只能1")
    @Max(value = 99, message = "班级最大只能99")
    @RequestParam(name = "classroom", required = true)
                          int classroom) {
                                  System.out.println(grade + "," + classroom);
 }
  }
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Validated
@Controller
public @interface ValidatedController {
}
