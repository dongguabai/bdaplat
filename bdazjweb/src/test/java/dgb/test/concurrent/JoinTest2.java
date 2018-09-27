package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/24 12:06
 */
public class JoinTest2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始执行");
        Thread thread1 = new Thread(() -> {
            System.out.println("thread--1开始执行！");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread--1执行完成！");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread--2开始执行！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread--2执行完成！");
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("主线程结束执行");
    }
}
