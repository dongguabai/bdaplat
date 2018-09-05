package dgb.test.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguabai
 * @date 2018/9/3 17:33
 */
public class ReentrantLockTest5 implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest5 reentrantLockTest5 = new ReentrantLockTest5();
        Thread t1 = new Thread(reentrantLockTest5,"thread1");
        Thread t2 = new Thread(reentrantLockTest5,"thread2");
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                //持有6秒
                TimeUnit.SECONDS.sleep(6);
            } else {
                System.out.println("线程【" + Thread.currentThread().getName() + "】加锁失败！！！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程【" + Thread.currentThread().getName() + "】进入finally");
            if (lock.isHeldByCurrentThread()) {
                System.out.println("线程【" + Thread.currentThread().getName() + "】开始释放锁lock1");
            }
        }
    }

}
