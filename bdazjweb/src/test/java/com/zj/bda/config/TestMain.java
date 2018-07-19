package com.zj.bda.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dongguabai
 * @date 2018-07-19 12:55
 */
public class TestMain {
    public static void main(String[] args) {
        User user = new User("张三","123");

        Parent parent = new Parent("张三","123");

        List<User> list = new ArrayList<>();
        User p = map(list);

        p.getPassword();

    }

    static <D> D map(Object t){
        return (D)t;
    }
}
