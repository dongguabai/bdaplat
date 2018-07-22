package com.zj.bda.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dongguabai
 * @date 2018-07-21 23:55
 */
@Controller
public class TestCsrfCobtroller {

    @RequestMapping("testCsrf")
    public String testCsrf(){
        return "testCsrf";
    }
}
