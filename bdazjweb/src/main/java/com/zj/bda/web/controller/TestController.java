package com.zj.bda.web.controller;

import com.zj.bda.common.unspecific.annotation.LocalLock;
import com.zj.bda.common.exception.UnLoginException;
import com.zj.bda.common.unspecific.util.JsonUtil;
import com.zj.bda.common.unspecific.util.SpringUtil;
import com.zj.bda.common.verification.util.ValidateUtil;
import com.zj.bda.persistence.entity.UnStrTag;
import com.zj.bda.persistence.mapper.UnStrTagMapper;
import com.zj.bda.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Dongguabai on 2018-06-10.
 */
@Controller
@Slf4j
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("testDate")
    public void testDate(@RequestParam("test")Date test){
        String s1 = test.toLocaleString();
        System.out.println(s1);
    }



    @RequestMapping("html")
    public Object testhtml(){
       return "register.html";
    }


    @RequestMapping("async2")
    public void testAsync2(){
        System.out.println("into   controller---");
        testService.test();
        System.out.println("end   controller---");
    }


    @Autowired
    private TestTaskAsync testTaskAsync;

    @RequestMapping("async")
    public void testAsync(){
        System.out.println("before============");
        testTaskAsync.testAsync03();
        System.out.println("after============");
    }

    @Autowired
    private UnStrTagMapper unStrTagMapper;

    /**
     * 主要是用返回主页
     *
     * @return
     */
//    @RequestMapping
//    public Object tets01() {
//        if (1 == 1) {
//            throw new RuntimeException("0000000000000000");
//        }
//        return "aaa";
//    }

    @RequestMapping("test/b")
    public String test02() {
        if (1 == 1) {
            throw new UnLoginException("aaaaaaaaaaaa");
        }
        return "spring boot";
    }
    @RequestMapping("test/c")
    public String test03() {
        System.out.println("进入Controller");
        List<UnStrTag> unStrTags = unStrTagMapper.selectAll();

        return JsonUtil.toJSON(unStrTags);
    }
    @RequestMapping("test/d")
    public Object test04() {
       testService.testTrans();

        return "ok";
    }
    @RequestMapping("test/e")
    public Object test06() {
        Object test01Controller = SpringUtil.getBean("test01Controller");


        return test01Controller.getClass();
    }

    @RequestMapping("login")
    public Object test05(@Valid User user,BindingResult result) {
        ValidateUtil.validateModel(result);
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
     //  testService.testTrans();

        return "ok";
    }

    @RequestMapping("test/f")
    @LocalLock(key = "")
    public Object test07() {
        int i2 = 1/0;
        Example ep = new Example(UnStrTag.class);
        for (int i = 0; i <200 ; i++) {
            String next = Sid.next();
            System.out.println(next);
            System.out.println("===================");
        }
        return "ok";
    }


}
