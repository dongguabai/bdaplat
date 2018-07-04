package com.zj.bda.web.controller.test;

import com.fasterxml.jackson.annotation.JsonView;
import com.zj.bda.common.exception.UnLoginException;
import com.zj.bda.common.restrict.annotation.LocalLock;
import com.zj.bda.common.validator.helper.ValidateHelper;
import com.zj.bda.persistence.entity.UnStrTag;
import com.zj.bda.persistence.mapper.UnStrTagMapper;
import com.zj.bda.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Dongguabai on 2018-06-10.
 */
@RestController
@Slf4j
public class TestController {

    /**
     * 指定当前Controller方法返回的是UserDetail指定的视图
     * @return
     */
    @JsonView(User.UserDetail.class)
    @RequestMapping("/UserDetail")
    public User getUserDetail(){
        User user = User.builder().userName("张三").password("123").build();
        return user;
    }
    /**
     * 指定当前Controller方法返回的是UserDetail指定的视图
     * @return
     */
    @JsonView(User.UserInfo.class)
    @RequestMapping("/UserInfo")
    public User getUserInfo(){
        User user = User.builder().userName("张三").password("123").build();
        return user;
    }


    @Autowired
    TestService testService;

    /*@RequestMapping("testDate")
    public void testDate(@RequestParam("test")Date test){
        String s1 = test.toLocaleString();
        System.out.println(s1);
    }*/


    @RequestMapping("test/ahttp")
    @ResponseBody
    public Callable<Object> testDate(@RequestParam("test")Date test){

        Callable<Object> callable = ()->{
            List<UnStrTag> unStrTags = unStrTagMapper.selectAll();
            log.info(Thread.currentThread().getName() + " 进入call方法");
            log.info(Thread.currentThread().getName() + " 从helloService方法返回");
            return unStrTags;
        };

        log.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;

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
    @CacheEvict(value = "localCache",key = "#user.userName",beforeInvocation = false)
    @Cacheable(value = "localCache",unless="#result == null")
    public Object test03() {
        System.out.println("进入Controller");
        List<UnStrTag> unStrTags = unStrTagMapper.selectAll();

        return unStrTags;
    }
    @RequestMapping("test/d")
    public Object test04() {
       testService.testTrans();

        return "ok";
    }
    @RequestMapping("test/{id:\\d+}")
    public Object test06(@PathVariable("id") String id) {
        System.out.println(id);
        return id;
    }

    @RequestMapping("login")
    public Object test05(@Valid User user,BindingResult result) {
        ValidateHelper.validateModel(result);
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
        Example ep = new Example(UnStrTag.class);
        for (int i = 0; i <200 ; i++) {
            String next = Sid.next();
            System.out.println(next);
            System.out.println("===================");
        }
        return "ok";
    }

    public static void main(String[] args) {
        User user = new User("aa","aa");
    }


}