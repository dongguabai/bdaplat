package dgb.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguabai
 * @date 2018/9/3 16:07
 */
public class ReentrantLockTest3 implements Runnable {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    @Override
    public void run() {
        System.out.println("线程【" + Thread.currentThread().getName() + "】开始执行---------------");
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("线程【" + Thread.currentThread().getName() + "】解锁---------------");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("线程【" + Thread.currentThread().getName() + "】开始执行---------------");
        ReentrantLockTest3 reentrantLockTest3 = new ReentrantLockTest3();
        Thread thread = new Thread(reentrantLockTest3);
        thread.start();
        Thread.sleep(5000);
        System.out.println("线程【" + Thread.currentThread().getName() + "】发出通知");
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
