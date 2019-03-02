package dgb.test.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author Dongguabai
 * @date 2018/9/30 11:45
 */
public class CyclicBarrierTest1 implements Runnable {

    public static final int COUNT = 5;

   // private static CyclicBarrier c = new CyclicBarrier(COUNT);
    private static CountDownLatch c = new CountDownLatch(COUNT);

    private static CyclicBarrierTest1 cyc = new CyclicBarrierTest1();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executor.execute(() -> {
                try {
                    dosth();
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    public static void dosth() throws BrokenBarrierException, InterruptedException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now() + "   " + Thread.currentThread().getName() + "  到了！再等等！");
       // c.await();
        c.countDown();
        c.await();
        System.out.println(LocalDateTime.now() + "   " + Thread.currentThread().getName() + "  执行了！");
    }

    @Override
    public void run() {
        try {
            dosth();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
