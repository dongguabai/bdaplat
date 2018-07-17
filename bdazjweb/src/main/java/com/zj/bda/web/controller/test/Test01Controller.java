package com.zj.bda.web.controller.test;

import com.zj.bda.common.validator.annotation.ValidatedController;
import com.zj.bda.common.validator.helper.ValidateHelper;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Dongguabai
 * @date 2018-07-08 1:42
 */
//@Controller
//@ValidatedController
@ValidatedController
public class Test01Controller {

    @RequestMapping(value = "test01")
    public String test01() {
        return "index";
    }

    /**
     * 如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。
     */
    @ResponseBody
    @RequestMapping(value = "/test0000", method = RequestMethod.GET)
    public Object demo6(@Range(min = 1, max = 9, message = "年级只能从1-9")
                      @RequestParam(name = "grade", required = true)
                              int grade,
                      @Min(value = 1, message = "班级最小只能1")
                      @Max(value = 99, message = "班级最大只能99")
                      @RequestParam(name = "classroom", required = true)
                              int classroom) {
        System.out.println(grade + "," + classroom);
        return "aaa";
    }

    @RequestMapping("login1")
    public Object test05(@Valid User user, BindingResult result) {
        ValidateHelper.validateModel(result);
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
        return "ok";
    }
}
