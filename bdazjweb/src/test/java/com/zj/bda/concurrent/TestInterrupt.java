package com.zj.bda.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguabai
 * @date 2018/9/21 22:35
 */
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("线程启动---------");
            while (true){
                try {
                    System.out.println("线程正在执行------");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(LocalDateTime.now()+"|    in Exception:  "+Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        },"Dongguabai");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(LocalDateTime.now()+"|   线程准备中断");
        thread.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(LocalDateTime.now()+"|  before:  "+thread.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after:   "+thread.isInterrupted());
    }
}
