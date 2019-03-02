package com.zj.bda.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Dongguabai
 * @date 2018-07-11 22:26
 */
@Service
@Slf4j
public class TTestAsync {

    @Async("asyncTaskExecutor")
    public void test1() throws InterruptedException {
        Thread.sleep(1000);
        log.info("1111111111111111111111");
    }

    @Async("asyncTaskExecutor")
    public void test2() throws InterruptedException {
        Thread.sleep(2000);
        log.info("2222222");
    }

    public void test3() throws InterruptedException {
        System.out.println("执行前-------");
        this.test1();
        this.test2();
        System.out.println("执行后-------");

    }
}
