package dgb.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguabai
 * @date 2018/9/3 11:46
 */
public class ReentrantLockTest2 implements Runnable {

    private static final ReentrantLock lock = new ReentrantLock(true);


    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println("线程【" + Thread.currentThread().getName() + "】获得锁-----");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest2 reentrantLockTest2 = new ReentrantLockTest2();
        new Thread(reentrantLockTest2,"thread-1").start();
        new Thread(reentrantLockTest2,"thread-2").start();
    }
}
