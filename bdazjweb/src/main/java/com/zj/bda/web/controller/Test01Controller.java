package com.zj.bda.web.controller;

import com.zj.bda.common.annotation.ValidatedController;
import com.zj.bda.web.result.OperaterResultHelper;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Dongguabai on 2018-06-14.
 */
//@Controller
//@ValidatedController
@ValidatedController
public class Test01Controller {

    @RequestMapping(value = "test01")
    @ResponseBody
    public Object test01() {
        return OperaterResultHelper.success();
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
}
