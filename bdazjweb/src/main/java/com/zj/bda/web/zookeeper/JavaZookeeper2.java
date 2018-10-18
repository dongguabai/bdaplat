package com.zj.bda.web.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguabai
 * @date 2018/10/8 15:20
 */
public class JavaZookeeper2 {

    static final String CONNECT_ADDR = "192.168.220.135:2181,192.168.220.136:2181,192.168.220.137:2181";
    static final int SESSION_OUTTIME = 2000;//ms

    public static void main(String[] args) {
        try {
            //连接
            ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, null);
            //获取状态
            System.out.println(zk.getState());
            TimeUnit.SECONDS.sleep(1);
            System.out.println(zk.getState());
            //关闭连接
            zk.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
