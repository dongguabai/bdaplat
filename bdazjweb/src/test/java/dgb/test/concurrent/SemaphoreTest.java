package dgb.test.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author Dongguabai
 * @date 2018/9/4 9:22
 */
public class SemaphoreTest implements Runnable{

    private final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire()) {
                System.out.println(LocalDateTime.now() + "---线程【" + Thread.currentThread().getName() + "】正在执行耗时的操作---");
                TimeUnit.SECONDS.sleep(1);
                semaphore.release();
            }else {
                System.out.println(LocalDateTime.now() + "---线程【" + Thread.currentThread().getName() + "】被丢弃");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreTest);
        }
    }
}
