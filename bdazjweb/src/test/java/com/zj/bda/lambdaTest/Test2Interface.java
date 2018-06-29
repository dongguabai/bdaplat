package com.zj.bda.lambdaTest;

/**
 * @author Dongguabai
 * @date 2018-06-28 14:31
 */
@FunctionalInterface
public interface Test2Interface<T,R> {

    String test(T t,R r);
}
