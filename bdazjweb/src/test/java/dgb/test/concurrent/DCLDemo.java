package dgb.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguabai
 * @date 2018/9/23 22:25
 */
@Slf4j
public class DCLDemo {

    private static volatile DCLTestBean instance;

    public static DCLTestBean getInstance() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-----");
        if (instance == null) {
            synchronized (DCLDemo.class) {
                if (instance == null) {
                    instance = new DCLTestBean();
                    System.out.println("new 了");
                    //耗时的初始化
                    TimeUnit.SECONDS.sleep(3);
                    instance.setPassword(123);
                    instance.setUsername("zhangsan");
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    DCLTestBean instance = DCLDemo.getInstance();
                    System.out.println(instance.getUsername().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        thread3.start();
    }
}
