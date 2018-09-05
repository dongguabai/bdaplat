package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/3 0:09
 */
public class DaemonTest {

    public static void main(String[] args) {
        System.out.println("用户线程开启----");
        Thread thread = new Thread(new DaemonThread());
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户线程退出---");
    }

    static class DaemonThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("守护线程正在执行-----");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
