package com.zj.bda.web.controller.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Dongguabai on 2018-06-23 11:28
 */
@Component
public class TestTaskAsync {

    @Async
    public void testAsync01(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务--testAsync0   1---执行");
    }

    @Async
    public void testAsync02(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务--testAsync0   2---执行");
    }

    @Async
    public void testAsync03(){
      this.testAsync01();
      this.testAsync02();
    }
}
