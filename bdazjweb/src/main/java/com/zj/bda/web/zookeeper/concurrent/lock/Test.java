package com.zj.bda.web.zookeeper.concurrent.lock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguabai
 * @date 2018/10/30 12:40
 */
public class Test {

    public static void main(String[] args) throws  IOException {
        for (int j = 0; j < 10; j++) {

            CountDownLatch countDownLatch = new CountDownLatch(10);
            for (int i = 1; i <= 10; i++) {
                new Thread(() -> {
                    try {
                        countDownLatch.await();
                        DistributedLock lock = new DistributedLock();
                        lock.lock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "Thread<" + i + ">").start();
                countDownLatch.countDown();
            }
           System.in.read();
        }

    }
}
