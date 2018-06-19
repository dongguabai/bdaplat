package com.zj.bda.web.controller;

import com.zj.bda.persistence.entity.UnStrTag;
import com.zj.bda.persistence.mapper.UnStrTagMapper;
import com.zj.bda.service.TestService;
import com.zj.bda.web.exception.UnLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Dongguabai on 2018-06-10.
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UnStrTagMapper unStrTagMapper;

    @Autowired
    private TestService testService;

    /**
     * 主要是用返回主页
     *
     * @return
     */
    @RequestMapping
    public Object tets01() {
        if (1 == 1) {
            throw new RuntimeException("0000000000000000");
        }
        return "aaa";
    }

    @RequestMapping("test/b")
    public String test02() {
        if (1 == 1) {
            throw new UnLoginException("aaaaaaaaaaaa");
        }
        return "spring boot";
    }
    @RequestMapping("test/c")
    public Object test03() {
        List<UnStrTag> unStrTags = unStrTagMapper.selectAll();

        return unStrTags;
    }
    @RequestMapping("test/d")
    public Object test04() {
       testService.testTrans();

        return "ok";
    }

}
