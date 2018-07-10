package com.zj.bda.lambdaTest;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dongguabai
 * @date 2018-07-09 17:41
 */
public class TestMethodRef {

    @Test
    public void test1() {
        //Lambda方法
        Test1Interface<String> test = (x) -> System.out.println(x);
        test.test("张三");

        //方法引用----对象::实例方法名
        PrintStream printStream = System.out;
        Test1Interface<String> test2 = printStream::println;
        test2.test("李四");

        Test1Interface<String> test3 = System.out::println;
        test3.test("李四");
    }

    @Test
    public void test2() {
        //比较，使用Lambda表达式
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);


        //方法引用----类::静态方法名
        Comparator<Integer> comparator2 = Integer::compare;
    }

    @Test
    public void test3() {
        //字符串equals----Lambda表达式
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        //方法引用----类::实例方法名
        BiPredicate<String, String> biPredicate2 = String::equals;

        boolean test = biPredicate2.test(null
                , "aaaAAA");

        System.out.println(test);
    }

    @Test
    public void test4() {
        //Lambda表达式
        Supplier<User> supplier = ()->new User();

        //函数式接口抽象方法为：T get();-----方法中没有参数
        //返回无参构造
        Supplier<User> supplier1 = User::new;

        //----------------------------有参构造--------------------------
        //Lambda表达式
        Function<Integer,User> function = (x)->new User(x);

        //函数式接口抽象方法为：R apply(T t);-----方法中有一个参数
        //返回有参构造
        Function<Integer,User> function1 = User::new;

    }

    @Test
    public void test5(){
        //Lambda表达式
        Function<Integer,String[]> function = (x)->new String[x];
        String[] apply = function.apply(20);
        System.out.println("数组的长度为："+apply.length);

        //--------数组引用
        Function<Integer,String[]> function1 = String[]::new;
        String[] apply2 = function.apply(20);
        System.out.println("数组的长度为："+apply2.length);
    }
}
