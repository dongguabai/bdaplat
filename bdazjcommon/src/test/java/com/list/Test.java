package com.list;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @author Dongguabai
 * @date 2018/11/19 22:50
 */
public class Test {

    public static void main(String[] args) throws IOException {
        int COUNT = 200000;
        //doSth(COUNT);

        //   List<String> list1 = Files.readLines(new File("C:\\Users\\Dongguabai\\Desktop\\a.txt"), Charset.defaultCharset());
       // List<String> list2 = Files.readLines(new File("C:\\Users\\Dongguabai\\Desktop\\b.txt"), Charset.defaultCharset());

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            //if (i <= 160000) {
                String s = String.valueOf(Math.random() * 11);
                list1.add(s);
                list2.add(s);
            /*}else {
                list1.add(String.valueOf(Math.random() * 11));
                list2.add(String.valueOf(Math.random() * 11));
            }*/
        }

        HashSet<String> set = new HashSet<>(COUNT * 2);
        set.addAll(list1);
        set.addAll(list2);
        long startTime = System.currentTimeMillis();
        set.removeAll(list1);
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

    }

    private static void doSth(int COUNT) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            String s = String.valueOf(Math.random() * 11);
            if (COUNT <= 20000) {
                list1.add(s);
                list2.add(s);
            } else {
                list1.add(String.valueOf(new Random().nextInt() * 11));
                list2.add(String.valueOf(new Random().nextInt() * 11));
            }
        }


        list1.add("a");
        HashSet<String> set = new HashSet<>(COUNT * 200);
        set.addAll(list1);
        set.addAll(list2);
        set.removeAll(list1);
    }
}
