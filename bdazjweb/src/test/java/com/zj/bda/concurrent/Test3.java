package com.zj.bda.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/22 21:48
 */
public class Test3 {

    private static Test3 instance = null;

    public static Test3 getInstance(){
        if (instance==null){
            instance = new Test3();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
