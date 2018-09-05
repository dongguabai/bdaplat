package dgb.test.concurrent;

import java.util.ArrayList;

/**
 * @author Dongguabai
 * @date 2018/8/31 16:32
 */
public class SynchronizedTest {

    private static Object object = new Object();

    private static void lock() {
        System.out.println(Thread.currentThread().getName() + "开始------------");
        synchronized (object) {
            object = new ArrayList<String>();

            System.out.println(Thread.currentThread().getName() + "进入synchronized------");
        }
        System.out.println(Thread.currentThread().getName() + "出synchronized");
    }

    public static void main(String[] args) {
        new Thread(new JoinTest.Thread1(), "张三").start();
        new Thread(new JoinTest.Thread1(), "李四").start();

    }

    static class Thread1 implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread().getName() + "==============");
            lock();
        }
    }
}
