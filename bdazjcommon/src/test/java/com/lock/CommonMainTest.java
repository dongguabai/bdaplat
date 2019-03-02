package com.lock;

/**
 * @author Dongguabai
 * @date 2018-07-25 9:46
 */
public class CommonMainTest {

    //分布式锁测试
   /* @Resource(name = "simpleOracleLock")
    private Lock simpleOracleLock;

    private int sum = 100;

    @Test
    public void test22() throws InterruptedException {
        RunnableImpl runnable = new RunnableImpl();
        Thread t1 = new Thread(runnable, "售票窗口一");
        Thread t2 = new Thread(runnable, "售票窗口二");
        Thread t3 = new Thread(runnable, "售票窗口三");
        Thread t4 = new Thread(runnable, "售票窗口四");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        //主线程等待子线程执行完毕
        Thread.currentThread().join();
    }

    class RunnableImpl implements Runnable {

        @Override
        public void run() {
            while (sum > 0) {
                simpleOracleLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "现在卖了第" + (sum--) + "张票");
                }catch (Exception e){

                }finally {
                    simpleOracleLock.unlock();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
