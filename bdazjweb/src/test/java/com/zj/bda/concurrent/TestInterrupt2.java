package com.zj.bda.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguabai
 * @date 2018/9/22 0:01
 */
public class TestInterrupt2 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <1000000 ; i+=2) {
            i--;
        }
        Thread thread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println("before:   "+thread.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after:   "+thread.isInterrupted());
    }
}
