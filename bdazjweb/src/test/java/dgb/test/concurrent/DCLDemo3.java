package dgb.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguabai
 * @date 2018/9/24 11:32
 */
@Slf4j
public class DCLDemo3 {

    private static volatile DCLTestBean instance;

    public static DCLTestBean getInstance() {
        if (instance == null) {   //第一次检查
            synchronized (DCLDemo3.class) {  //锁
                if (instance == null) {   //第二次检查
                    instance = new DCLTestBean();    //可能出现问题的地方
                }
            }
        }
        return instance;
    }

    static int count = 10;
    private static CountDownLatch countDownLatch = new CountDownLatch(count);

    public static void main(String[] args) {
       /* for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(10) * 1000);
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getInstance();
            }, "thread-" + i).start();
            countDownLatch.countDown();
        }*/
    }
}
