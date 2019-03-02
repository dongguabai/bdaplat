package dgb.test.concurrent;

import java.time.LocalDateTime;

/**
 * @author Dongguabai
 * @date 2018/9/2 19:35
 */
public class SuspendTest2 {

    private static final Object u = new Object();

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread c1 = new ChangeObjectThread("thread-1");
        ReadThread r = new ReadThread();

        c1.start();
        r.start();
        Thread.sleep(1000);
        c1.mySuspend();
        Thread.sleep(60000);
        c1.myResume();
    }

    public static class ChangeObjectThread extends Thread {

        private volatile boolean mySuspend = false;

        public ChangeObjectThread(String name) {
            super(name);
        }

        public void mySuspend() {
            // System.out.println("线程【" + getName() + "】mySuspend==");
            mySuspend = true;
        }

        public void myResume() {
            //System.out.println("线程【" + getName() + "】myResume==");
            mySuspend = false;
            synchronized (this) {
                this.notify();
                //System.out.println("线程【" + getName() + "】执行notify==");
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (mySuspend) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (u) {
                    System.out.println("线程【" + getName() + "】正在执行，当前时间：" + LocalDateTime.now());
                }
                Thread.yield();
            }
        }
    }

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    System.out.println("ReadThread执行。。。。");
                }
                Thread.yield();
            }
        }
    }
}
