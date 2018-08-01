package com.zj.bda.web.controller.test.util;

/**
 * @author Dongguabai
 * @date 2018/7/31 22:17
 */
public class TestID {

    public static void main(String[] args) {
        IdCardGenerator g = new IdCardGenerator();

        String generate = g.generate();
        System.out.println(generate);
    }
}
