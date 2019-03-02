package dgb.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguabai
 * @date 2018/9/3 11:10
 */
public class ReentrantLockTest implements Runnable {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }
}
