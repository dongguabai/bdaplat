package com.zj.bda;

import com.zj.bda.persistence.mapper.TestEmpty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dongguabai
 * @date 2018/10/8 10:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSpringTest {
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }


    @Autowired
    private TestEmpty testEmpty;

    @Test
    public void test1(){
        System.out.println("测试test1-------------");
        testEmpty.test1("1");
        testEmpty.test1("aa");
        System.out.println("测试test2-------------");
        testEmpty.test2("");
        testEmpty.test2(null);
    }

    public static void main(String[] args) {
        System.out.println(1<<3);
    }

}
