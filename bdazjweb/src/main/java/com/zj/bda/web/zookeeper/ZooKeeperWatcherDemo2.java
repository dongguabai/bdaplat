package com.zj.bda.web.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguabai
 * @date 2018/10/9 19:18
 */
public class ZooKeeperWatcherDemo2 {
    static final String CONNECT_ADDR = "192.168.220.135:2181,192.168.220.136:2181,192.168.220.137:2181";
    static final int SESSION_OUTTIME = 2000;//ms
    /**
     * 阻塞程序执行，等待zookeeper连接成功
     */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    static final String PATH = "/dongguabai";
    static final String PATH_CHILD = PATH + "/child";

    public static void main(String[] args) {
        try {
            //连接
            ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, event -> {
                System.out.println("事件是：" + event.getType());
                //如果收到了服务端的响应事件，连接成功
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                    connectedSemaphore.countDown();
                }
            });
            connectedSemaphore.await();
            System.out.println("连接成功！");

            //binding
            zk.exists(PATH_CHILD, event -> {
                System.out.println("create path child---" + event.getType());
            });

            zk.exists(PATH, event -> {
                System.out.println("create path---" + event.getType());
            });

            System.out.println("create path");
            //create
            zk.create(PATH, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zk.getChildren(PATH, event -> {
                System.out.println("create getChild---" + event.getType());
            });

            System.out.println("create path child");
            zk.create(PATH_CHILD, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

