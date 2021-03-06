package com.zj.bda;

import com.zj.bda.common.util.SpringUtil;
import com.zj.bda.persistence.grace.handler.MapResultHandler;
import com.zj.bda.persistence.mapper.TestEmpty;
import com.zj.bda.persistence.query.UserQuery;
import com.zj.bda.web.controller.test.Test01Controller;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test2(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        MapResultHandler handler = new MapResultHandler();
        UserQuery userQuery = new UserQuery("zhangsan",2000);
        sqlSession.select("com.zj.bda.persistence.mapper.UserAccountMapper.testType",userQuery,handler);
        //获取结果
        handler.getMappedResults().forEach((k,v)-> System.out.println("k:"+k+",v:"+v));
    }

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
        Test01Controller bean = SpringUtil.getBean(Test01Controller.class);

    }

    public static void main(String[] args) {
        System.out.println(1<<3);
    }

}
