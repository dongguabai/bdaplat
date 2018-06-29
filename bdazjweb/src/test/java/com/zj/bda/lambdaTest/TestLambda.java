package com.zj.bda.lambdaTest;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Dongguabai on 2018-06-20 14:13
 */
public class TestLambda {

    List<User> users = Lists.newArrayList(new User("张三", "123", 20),
            new User("李四", "222", 20),
            new User("王五", "235", 50));

    @Test
    public void testFor(){

        System.out.println("原来的使用-------------------");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("Lambda的使用-------------------");
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void test01() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

    }

    @Test
    public void test1(){
        System.out.println("原来的写法------------------");
        Runnable rb = new Runnable() {
            @Override
            public void run() {
                System.out.println("执行了Runnable方法-------");
            }
        };
        rb.run();
        System.out.println("Lambda的写法------------------");

        Runnable rbLambda = ()-> System.out.println("执行了Lambda的Runnable方法-------");

        rbLambda.run();
    }

    @Test
    public void test2(){
        Test1Interface<String> t1i  = (x)->System.out.println("-----  "+x+"  --------");
        t1i.test("张三");
    }

    @Test
    public void test3(){
        //Test1Interface<String> t1i  = (x)->System.out.println("-----  "+x+"  --------");

        //参数没有写括号
        Test1Interface<String> t1i  = x->System.out.println("-----  "+x+"  --------");
        t1i.test("张三");
    }

    @Test
    public void test4(){
        Test2Interface<String, Integer> t2i = (x, y) -> {
            if (y<0){
                return x;
            }
            System.out.println("测试Lambda-------");
            return x+":"+y;
        };

        String s1 = t2i.test("张三", -1);
        System.out.println(s1);
        System.out.println("----------------");
        String s2  = t2i.test("李四",2);
        System.out.println(s2);
    }

    @Test
    public void test5(){
        Test2Interface<String, Integer> t2i = (x, y) -> x+y;
        String s = t2i.test("张三", 1000);
        System.out.println(s);
    }
}
