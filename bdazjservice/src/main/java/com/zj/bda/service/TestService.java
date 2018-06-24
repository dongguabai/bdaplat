package com.zj.bda.service;

import com.zj.bda.persistence.mapper.UnStrTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dongguabai on 2018-06-19 11:15
 */
@Service
@Transactional
public class TestService {

    public void test(){
//        System.out.println("before--test");
//        TestService testService = (TestService) AopContext.currentProxy();
//        TestService tt = SpringUtil.getBean(TestService.class);
//        System.out.println(tt == testService?"Spring容器中的类对象与当前代理类是同一个对象":"Spring容器中的类对象与当前代理类不是同一个对象");
//        testService.test02();
//        testService.test03();
//        System.out.println("end--test");
    }


    @Async
    public void test02(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test02执行------------------");
    }

    @Async
    public void test03(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test03执行------------------");
    }


    @Autowired
    private UnStrTagMapper unStrTagMapper;


    public void testTrans(){
        int i = unStrTagMapper.deleteByPrimaryKey(31);
        int o = 1/0;
        System.out.println(i);
    }
}
