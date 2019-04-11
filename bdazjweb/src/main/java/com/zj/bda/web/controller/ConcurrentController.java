package com.zj.bda.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dongguabai
 * @date 2018/8/28 17:09
 */
@RestController
@Slf4j
public class ConcurrentController {

    @Autowired
    private TTestAsync tTestAsync;

    @RequestMapping("/test1")
    public Object test1() throws InterruptedException {
        tTestAsync.test3();
       // int i = 1/0;
        return "OK";
    }
}
