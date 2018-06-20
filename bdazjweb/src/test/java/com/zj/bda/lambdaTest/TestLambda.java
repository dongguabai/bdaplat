package com.zj.bda.lambdaTest;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by Dongguabai on 2018-06-20 14:13
 */
public class TestLambda {


    @Test
    public void test01(){
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};

        List<String> list = Lists.newArrayList("Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro");
        String join = Joiner.on(",").join(list.iterator().next(),"aa");

        System.out.println(join);
    }

    @Test
    public void test02(){
        int x = 1;
        int y = 2;

    }
}
