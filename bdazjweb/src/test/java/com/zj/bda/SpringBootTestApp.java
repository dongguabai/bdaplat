package com.zj.bda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dongguabai
 * @date 2018-06-30 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestApp {

    @Value("${uname}")
    private String string;

    @Test
    public void test1(){
        System.out.println(string);
    }
}
