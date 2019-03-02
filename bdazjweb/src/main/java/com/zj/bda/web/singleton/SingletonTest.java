package com.zj.bda.web.singleton;

/**
 * @author Dongguabai
 * @date 2018-07-24 15:28
 */
public class SingletonTest {

    private boolean initialized = false;

    /**
     * 反射问题
     */
    private SingletonTest(){
        synchronized (SingletonTest.class){
            if (initialized == false){
                initialized = !initialized;
            }else {
                throw new RuntimeException("单例已被侵犯！");
            }
        }
    }

    public final static SingletonTest getInstance(){
        return SingletonTestInnerClasses.SINGLETONTEST;
    }

    private static class SingletonTestInnerClasses{
        private static final SingletonTest SINGLETONTEST = new SingletonTest();
    }

    private Object readResolve(){
        return getInstance();
    }
}
