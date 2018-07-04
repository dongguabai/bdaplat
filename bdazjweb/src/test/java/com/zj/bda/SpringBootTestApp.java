package com.zj.bda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dongguabai
 * @date 2018-06-30 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestApp {

    @Value("${form.login.page}")
    private String[] strings;

    @Value("${form.login.page}")
    private String[] string;

    @Autowired
    private Environment env;

    @Test
    public void test1(){
        System.out.println("使用数组接收----------");
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("使用字符串接收----------");
        System.out.println(string);
        System.out.println("使用Environment接收----------");
        String property = env.getProperty("form.login.page");
        System.out.println(property);

    }
}
