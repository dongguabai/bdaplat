package com.zj.bda;

import com.zj.bda.dgbsecurity.grace.properties.DgbSecurityProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dongguabai
 * @date 2018-06-30 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestApp {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Test
    public void test01(){
        System.out.println(dgbSecurityProperties.getBrowser().getLoginAction());
        System.out.println(dgbSecurityProperties.getBrowser().getLoginUrl());
        System.out.println(dgbSecurityProperties.getBrowser().getLoginPage());
    }
}
