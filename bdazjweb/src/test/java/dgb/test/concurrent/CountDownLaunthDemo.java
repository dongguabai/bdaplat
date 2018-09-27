package dgb.test.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dongguabai
 * @date 2018/9/24 11:46
 */
public class CountDownLaunthDemo implements Runnable{

    static int count = 10;

    static CountDownLatch end = new CountDownLatch(count);
    static CountDownLaunthDemo demo = new CountDownLaunthDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            exec.submit(demo);
        }
        end.await();
        System.out.println("-------------Fire");
        exec.shutdownNow();
    }
}
