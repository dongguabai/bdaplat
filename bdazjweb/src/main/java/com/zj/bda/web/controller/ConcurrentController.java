package com.zj.bda.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dongguabai
 * @date 2018/8/28 17:09
 */
@RestController
@Slf4j
public class ConcurrentController {

    @RequestMapping("/test1")
    public Object test1(){
        return "OK";
    }
}
