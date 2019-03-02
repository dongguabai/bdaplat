package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/1 22:37
 */
public class MultiThreadLong {

    public static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {
        @Override
        public void run() {
            while (true) {
                long temp = t;
                if (temp != 111L && temp != 1111L && temp != 11111L) {
                    System.out.println(temp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(1111L)).start();
        new Thread(new ChangeT(11111L)).start();
        new Thread(new ReadT()).start();
    }
}
