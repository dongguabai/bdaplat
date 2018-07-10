package com.zj.bda;

import com.zj.bda.common.init.IInitExpand;
import com.zj.bda.common.unspecific.util.SpringUtil;
import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018-06-30 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestApp {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Autowired
    private Map<String,IInitExpand> map;

    @Test
    public void test01(){
        System.out.println(dgbSecurityProperties.getBrowser().getLoginAction());
        System.out.println(dgbSecurityProperties.getBrowser().getLoginUrl());
        System.out.println(dgbSecurityProperties.getBrowser().getLoginPage());

        map.forEach((k,v)->{
            System.out.println("key:"+k+":value:"+v);
        });

    }

    @Test
    public void test02(){
        Map<String, IInitExpand> beansOfType = SpringUtil.getBeansOfType(IInitExpand.class);
        beansOfType.forEach((k,v)->{
            System.out.println("key:"+k+":value:"+v);
        });

    }
}
