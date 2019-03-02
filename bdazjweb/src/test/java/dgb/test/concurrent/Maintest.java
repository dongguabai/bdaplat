package dgb.test.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguabai
 * @date 2018/8/30 10:11
 */
public class Maintest {

    @Test
    public void test1() throws InterruptedException {
        Thread thread1 = new Thread1();
       // Thread thread2 = new Thread1();
      //  thread1.setPriority(Thread.MIN_PRIORITY);
       // thread2.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        Thread.sleep(2000);
       // thread2.start();
    }

    class Thread1 extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread1--" + i);
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread1--" + i);
                    //Thread.yield();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           // System.out.println("---------------------------");
        }
    }

    class Thread2 extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2--" + i);
                //Thread.yield();
            }
        }
    }
}
