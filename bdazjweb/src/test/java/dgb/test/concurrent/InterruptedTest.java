package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/2 23:13
 */
public class InterruptedTest {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("线程已被中断-------");
                    break;
                }
                System.out.println("正在执行------------");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //清除中断标记
                    Thread.currentThread().interrupt();
                }
            }
        }
        );
        thread.start();
        Thread.sleep(1000);
        System.out.println("中断。。。。。");
        thread.interrupt();
    }
}
