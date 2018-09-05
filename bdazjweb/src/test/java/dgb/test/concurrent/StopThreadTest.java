package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/2 21:37
 */
public class StopThreadTest extends Thread {

    volatile boolean stopMe = false;

    public void stopMe(){
        stopMe = true;
    }

    @Override
    public void run() {
        while (true){
            if (stopMe){
                System.out.println("线程【"+this.getName()+"】stop......");
                break;
            }
            //do sth
            System.out.println("正在执行！！！");
            Thread.yield();
        }
    }
}
