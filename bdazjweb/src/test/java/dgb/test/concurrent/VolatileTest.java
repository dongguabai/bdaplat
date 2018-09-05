package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/1 23:04
 */
public class VolatileTest {

    static volatile int i;

    public static class AddTask implements Runnable{

        @Override
        public void run() {
            for (int j = 0;j<10000;j++){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j <1000 ; j++) {
            Thread thread1 = new Thread(new AddTask());
            Thread thread2= new Thread(new AddTask());
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println(i);
            i = 0;
        }
    }
}
