package dgb.test.concurrent;

import java.lang.management.ManagementFactory;

/**
 * @author Dongguabai
 * @date 2018/9/2 19:35
 */
public class SuspendTest {

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread c1 = new ChangeObjectThread("thread-1");
        ChangeObjectThread c2 = new ChangeObjectThread("thread-2");

        c1.start();
        c2.start();
       // Thread.sleep(1000);
        c1.resume();
        c2.resume();

        System.out.println("Pid is:" + ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
    }

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("线程【" + getName() + "】正在执行");
            Thread.currentThread().suspend();
        }
    }
}
