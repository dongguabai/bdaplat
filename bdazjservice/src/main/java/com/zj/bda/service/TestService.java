package com.zj.bda.service;

import com.zj.bda.persistence.mapper.UnStrTagMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by Dongguabai on 2018-06-19 11:15
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class TestService {


   @Async
    public void trancationnal(){
       System.out.println("开始执行异步耗时方法----");
       try {
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("耗时方法执行完毕----");
   }

    public void unTrancationnal(){
        System.out.println("普通方法开始执行");
        TestService o = (TestService) AopContext.currentProxy();
        o.trancationnal();
        System.out.println("普通方法执行完毕.....");

    }

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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

}
