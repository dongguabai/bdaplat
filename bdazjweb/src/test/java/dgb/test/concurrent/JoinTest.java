package dgb.test.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dongguabai
 * @date 2018/8/31 15:17
 */
@Slf4j
public class JoinTest {

    private static volatile int i = 0;

    static class Thread1 extends Thread{
        @Override
        public void run() {
            log.info("Thread1线程开始运行！！！");
            for (i=0;i<10000000;i++);
            log.info("Thread1线程运行结束！！！");
        }
    }

    public static void main(String[] args) throws InterruptedException {


        log.info("主线程开启-------------");
        Thread1 t1 = new Thread1();
        t1.start();
        t1.join();
        log.info("主线程结束-------------{}",i);
    }

}
