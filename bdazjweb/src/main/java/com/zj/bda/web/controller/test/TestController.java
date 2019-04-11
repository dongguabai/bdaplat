package com.zj.bda.web.controller.test;

import com.fasterxml.jackson.annotation.JsonView;
import com.zj.bda.common.concurrent.restrict.LocalLock;
import com.zj.bda.common.web.ServerResponseHelper;
import com.zj.bda.common.web.ServerResponse;
import com.zj.bda.persistence.entity.UnStrTag;
import com.zj.bda.persistence.mapper.UnStrTagMapper;
import com.zj.bda.service.TestService;
import com.zj.bda.web.controller.TTestAsync;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import wm.dgb.security.grace.exception.UnLoginException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Dongguabai on 2018-06-10.
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    TestService testService;
    @Autowired
    private TTestAsync tTestAsync;
    @Autowired
    private TestTaskAsync testTaskAsync;
    @Autowired
    private UnStrTagMapper unStrTagMapper;

    public static void main(String[] args) {
        User user = new User("aa", "aa");
    }

    @RequestMapping("/async/test01")
    public ServerResponse asyncTest01() throws Exception {
        log.info("开始处理请求--------------");

        Thread.sleep(3000);

        log.info("请求处理结束--------------");
        return ServerResponseHelper.success();
    }

    @RequestMapping("/async/test02")
    public Callable<ServerResponse> asyncTest02() {
        log.info("开始处理请求--------------");

        Callable<ServerResponse> result = ()->{
            log.info("副线程-------开始处理请求--------------");
            Thread.sleep(3000);
            log.info("副线程-------请求处理结束--------------");
            return ServerResponseHelper.success();
        };

        log.info("请求处理结束--------------");
        return result;
    }

    /*@RequestMapping("testDate")
    public void testDate(@RequestParam("test")Date test){
        String s1 = test.toLocaleString();
        System.out.println(s1);
    }*/

    @RequestMapping("/async/test")
    public void ttttt() throws Exception {
        System.out.println("前-----");
        tTestAsync.test1();
        tTestAsync.test2();
        System.out.println("后-----");

    }

    /**
     * 指定当前Controller方法返回的是UserDetail指定的视图
     *
     * @return
     */
    @JsonView(User.UserDetail.class)
    @RequestMapping("/UserDetail")
    public User getUserDetail() {
        User user = User.builder().userName("张三").password("123").build();
        return user;
    }

    /**
     * 指定当前Controller方法返回的是UserDetail指定的视图
     *
     * @return
     */
    @JsonView(User.UserInfo.class)
    @RequestMapping("/UserInfo")
    public User getUserInfo() {
        User user = User.builder().userName("张三").password("123").build();
        return user;
    }

    @RequestMapping("test/ahttp")
    @ResponseBody
    public Callable<Object> testDate(@RequestParam("test") Date test) {

        Callable<Object> callable = () -> {
            List<UnStrTag> unStrTags = unStrTagMapper.selectAll();
            log.info(Thread.currentThread().getName() + " 进入call方法");
            log.info(Thread.currentThread().getName() + " 从helloService方法返回");
            return unStrTags;
        };

        log.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;

    }

    @RequestMapping("html")
    public Object testhtml() {
        return "register.html";
    }

    @RequestMapping("async2")
    public void testAsync2() {
        System.out.println("into   controller---");
        testService.test();
        System.out.println("end   controller---");
    }

    @RequestMapping("async")
    public void testAsync() {
        System.out.println("before============");
        testTaskAsync.testAsync03();
        System.out.println("after============");
    }

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
    @CacheEvict(value = "localCache", key = "#user.userName", beforeInvocation = false)
    @Cacheable(value = "localCache", unless = "#result == null")
    public Object test03() {
        System.out.println("进入Controller");
        List<UnStrTag> unStrTags = unStrTagMapper.selectAll();

        return unStrTags;
    }

    @RequestMapping("test/c2")
    public Object test032() {
        UnStrTag unStrTag = new UnStrTag("kv", "1", "tag", "1", new Date(), null);
        unStrTagMapper.insertSelective(unStrTag);
        System.out.println("进入Controller2");
        List<UnStrTag> unStrTags = unStrTagMapper.selectAll();
        return ServerResponseHelper.success(unStrTags);
    }



    @RequestMapping("test/{id:\\d+}")
    public Object test06(@PathVariable("id") String id) {
        System.out.println(id);
        return id;
    }

    @RequestMapping("test/f")
    @LocalLock(key = "")
    public Object test07() {
        Example ep = new Example(UnStrTag.class);
        for (int i = 0; i < 200; i++) {
            String next = Sid.next();
            System.out.println(next);
            System.out.println("===================");
        }
        return "ok";
    }


}
