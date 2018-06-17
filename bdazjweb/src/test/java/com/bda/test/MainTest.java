package com.bda.test;

import com.bda.test.model.Person;
import com.google.common.base.Objects;
import com.zj.bda.common.util.JsonUtil;
import com.zj.bda.web.result.ViewResultUtil;
import org.junit.Test;

/**
 * Created by Dongguabai on 2018-06-14.
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println(JsonUtil.toJSON(ViewResultUtil.success("abc")));
        System.out.println("=================");
        System.out.println(JsonUtil.toJSON(ViewResultUtil.success("abc")));

    }

    @Test
    public void test01(){
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, null));
    }

    @Test
    public void testToString(){
        Person p = new Person();
        p.setPassword("123");
        p.setUserName("张三");
        System.out.println(p);
    }
}
