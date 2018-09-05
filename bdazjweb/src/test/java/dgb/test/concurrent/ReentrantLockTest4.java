package dgb.test.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguabai
 * @date 2018/9/3 17:33
 */
public class ReentrantLockTest4 implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    public ReentrantLockTest4(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始获取锁lock1");
                lock1.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始获取锁lock2");
                lock2.lockInterruptibly();
            }else {
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始获取锁lock2");
                lock2.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始获取锁lock1");
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("线程【"+Thread.currentThread().getName()+"】进入finally");
            if (lock1.isHeldByCurrentThread()){
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始释放锁lock1");
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()){
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始释放锁lock2");
                lock2.unlock();
            }
            System.out.println("线程【"+Thread.currentThread().getName()+"】退出-------");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest4 test1 = new ReentrantLockTest4(1);
        ReentrantLockTest4 test2 = new ReentrantLockTest4(2);
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test2);
        t1.start();
        t2.start();
    }
}
