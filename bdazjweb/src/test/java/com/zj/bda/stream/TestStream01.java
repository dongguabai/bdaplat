package com.zj.bda.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Dongguabai
 * @date 2018-07-19 10:15
 */
public class TestStream01 {

    @Test
    public void test01(){
        //1.通过Collection系列集合提供的stream()或parallelStream()方法
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");
            //创建串行流
        Stream<String> stream = list.stream();
            //创建并行流
        Stream<String> stringStream = list.parallelStream();

        //2.通过Arrays的静态方法
        IntStream stream1 = Arrays.stream(new int[]{1, 2, 3});

       //3.通过Stream类的静态方法of()
        Stream<String> stream2 = Stream.of("a", "b");

        //4.创建无限流
       // Stream.iterate(123, x -> x + 2).forEach(System.out::println);


        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);




    }
}
