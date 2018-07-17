package com.zj.bda;

import com.zj.bda.common.util.CusAccessUtil;
import com.zj.bda.web.controller.TTestAsync;
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
    TTestAsync tTestAsync;

 /*   @Autowired
    GraphicalCaptchaProcessGor GraphicalCaptchaProcessGor;*/

    @Test
    public void testCoreConfig() throws Exception {
    }

    @Test
    public void test02() throws InterruptedException {
            String ipAddress = CusAccessUtil.getIpAddress();
            System.out.println("---------"+ipAddress);
    }


    @Test
    public void test012() throws InterruptedException {
        System.out.println("前-----");
        tTestAsync.test1();
        tTestAsync.test2();
        System.out.println("后-----");
    }


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("a.b.c");
        System.out.println(aClass);
    }
}
