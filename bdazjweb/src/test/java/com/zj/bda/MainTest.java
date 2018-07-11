package com.zj.bda;

import com.zj.bda.dgbsecurity.captcha.graphical.process.GraphicalCaptchaProcessGor;
import com.zj.bda.web.controller.test.TestTaskAsync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Dongguabai on 2018-06-14.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainTest {

    @Autowired
    TestTaskAsync testTaskAsync;

    @Autowired
    GraphicalCaptchaProcessGor GraphicalCaptchaProcessGor;

    @Test
    public void testCoreConfig() throws Exception {
   //     GraphicalCaptchaProcessGor.create(null);


        System.out.println("before---------");
        testTaskAsync.testAsync03();
        System.out.println("after---------");
    }

}
