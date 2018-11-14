package dgb.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dongguabai
 * @date 2018/10/18 21:19
 */
public class Child extends Parent{

    @Override
    public void aaaaa() {
        System.out.println("ccccc------");
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("a");
        Set<String> set = new HashSet<>(list);
        System.out.println(set);
    }
}
