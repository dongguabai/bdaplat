package com.zj.bda;

import com.zj.bda.common.concurrent.lock.SimpleOracleLock;
import com.zj.bda.common.concurrent.support.OracleLockMapper;
import com.zj.bda.common.util.CusAccessUtil;
import com.zj.bda.persistence.mapper.UnStrTagMapper;
import com.zj.bda.web.controller.TTestAsync;
import com.zj.bda.web.controller.test.TestTaskAsync;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

/**
 * Created by Dongguabai on 2018-06-14.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MainTest {

    @Autowired
    TestTaskAsync testTaskAsync;

    @Autowired
    TTestAsync tTestAsync;

 /*   @Autowired
    GraphicalCaptchaProcessGor GraphicalCaptchaProcessGor;*/
    @Autowired
    private UnStrTagMapper unStrTagMapper;
    private int sum = 100;

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("a.b.c");
        System.out.println(aClass);
    }

    @Test
    public void testCoreConfig() throws Exception {
    }

    @Test
    public void test02() throws InterruptedException {
        String ipAddress = CusAccessUtil.getIpAddress();
        System.out.println("---------" + ipAddress);
    }

    @Test
    public void test012() throws InterruptedException {
        System.out.println("前-----");
        tTestAsync.test1();
        tTestAsync.test2();
        System.out.println("后-----");
    }

    @Test
    public void test11() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-------------");
        }, "线程名称11");

        t1.start();
    }


    @Autowired
    private OracleLockMapper oracleLockMapper;

    @Test
    public void testMapper(){
        oracleLockMapper.insert(SimpleOracleLock.LOCK_ORACLE_ENTITY);
    }

    @Test
    public void testMapper2(){
        oracleLockMapper.deleteByPrimaryKey("1");
    }




    //分布式锁测试
    @Resource(name = "simpleOracleLock")
    private Lock simpleOracleLock;

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
    }




}
