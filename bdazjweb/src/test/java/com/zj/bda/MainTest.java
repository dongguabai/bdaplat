package com.zj.bda;

import com.google.common.base.Objects;
import com.zj.bda.lombackTest.User;
import com.zj.bda.model.Person;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Dongguabai on 2018-06-14.
 */
public class MainTest {
        public static void main(String[] args) {
            System.out.println(randomString(-229985452) + " " + randomString(-147909649));
        }

        public static String randomString(int seed){
            Random rand = new Random(seed);
            StringBuilder sb = new StringBuilder();
            while(true){
                int n = rand.nextInt(27);
                if(n==0) break;
                sb.append((char)('`'+n));

            }
            return sb.toString();
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

    @Test
    public void  test09(){
        User user = new User();
        user.setPassword("zzz");
        user.setUserName("name");
        System.out.println(user);
    }
}
