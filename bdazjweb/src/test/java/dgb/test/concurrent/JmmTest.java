package dgb.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguabai
 * @date 2018/9/2 0:21
 */
public class JmmTest {

    public static int count = 0;
    private static CountDownLatch connectedSemaphore = new CountDownLatch(10000);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (JmmTest.class) {
                        count++;
                        connectedSemaphore.countDown();
                    }
                }
            }).start();
        }
        connectedSemaphore.await();
        System.out.println(connectedSemaphore);
    }
}
