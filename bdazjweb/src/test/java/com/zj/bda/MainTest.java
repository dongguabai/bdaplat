package com.zj.bda;

import com.zj.bda.common.concurrent.lock.SimpleOracleLock;
import com.zj.bda.common.concurrent.lock.support.OracleLockMapper;
import com.zj.bda.common.util.HttpClientUtil;
import com.zj.bda.common.util.IpUtil;
import com.zj.bda.persistence.mapper.IdCardMapper;
import com.zj.bda.persistence.mapper.UnStrTagMapper;
import com.zj.bda.web.controller.TTestAsync;
import com.zj.bda.web.controller.test.TestTaskAsync;
import com.zj.bda.web.controller.test.util.IdCardGenerator;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
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
        String ipAddress = IpUtil.getIpAddress();
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
    private IdCardMapper idCardMapper;
    @Test
    public void testIdCard() throws JSONException, UnsupportedEncodingException {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);

        List<String> sqlErrorList = new LinkedList<>();
        List<String> netErrorList = new LinkedList<>();
       IdCardGenerator idCardGenerator = new IdCardGenerator();
        int sqlErrorCount = 0;
        int netErrorCount = 0;
        int total = 100000;
        for (int i = 0; i < total; i++) {
            String checkIdCard = idCardGenerator.generate();
            int checkResult = idCardMapper.checkIdCardSelect(checkIdCard);
            if (checkResult!=1){
                sqlErrorCount++;
                sqlErrorList.add(checkIdCard);
                System.out.println("存储函数验证身份证验证出错，当前身份证为："+checkIdCard);
                continue;
            }
            String netResultJson = HttpClientUtil.sendHttpGet("http://www.dffyw.com/sfzcx/query.php?id=" + checkIdCard);
            if (!netResultJson.contains("\"Error\":\"\"") || !netResultJson.contains("\"Warning\":\"\"")){
                netErrorList.add(checkIdCard);
                netErrorCount++;
                System.out.println("出现存储函数验证身份证正确，但是网站验证身份错误，当前身份证为："+checkIdCard);
            }
        }
        System.out.println("共验证"+total+"次，存储函数验证错误次数为："+sqlErrorCount+"，错误率："+numberFormat.format((float) sqlErrorCount / (float) total * 100)+"%");
        System.out.println("其中出现存储函数验证身份证正确，但是网站验证身份错误次数为："+netErrorCount);

        System.out.println("存储函数验证错误身份证号为："+sqlErrorList);
        System.out.println("存储函数验证身份证正确，但是网站验证身份错误身份证号为："+netErrorList);
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

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public  void  testPasswordEncoder(){
        String s = "admin";
        System.out.println(passwordEncoder.encode(s));

    }






}
