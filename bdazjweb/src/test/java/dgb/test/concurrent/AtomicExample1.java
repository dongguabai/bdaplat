package dgb.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dongguabai
 * @date 2018/8/30 13:17
 */
@Slf4j
public class AtomicExample1 {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(()->{
                count.getAndAdd(j);
            }).start();
        }
        Thread.sleep(2000);
        log.info("最终结果：{}",count.get());
    }

    static class User{
        private String userName;
        private String passWord;
        private Integer age;

    }
}
