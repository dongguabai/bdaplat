package com.zj.bda.web.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguabai
 * @date 2018/10/8 18:04
 */
public class ZooKeeperWatcherDemo {
    static final String CONNECT_ADDR = "192.168.220.135:2181,192.168.220.136:2181,192.168.220.137:2181";
    static final int SESSION_OUTTIME = 2000;//ms
    /**
     * 阻塞程序执行，等待zookeeper连接成功
     */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    static final String PATH = "/dongguabai";

    public static void main(String[] args) {
        try {
            //连接
            ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, event -> {
                System.out.println("事件是："+event.getType());
                //如果收到了服务端的响应事件，连接成功
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                    connectedSemaphore.countDown();
                }
            });
            connectedSemaphore.await();
            System.out.println("连接成功！");
            System.out.println(zk.getState());

            //创建节点
           zk.create(PATH, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //可以绑定事件的操作：getData、exists、getChildren
            // 通过exists绑定事件
                //如果第二个参数为true,事件会触发至创建该ZooKeeper中的Watcher中
            //Stat stat = zk.exists(PATH,true);
            //在当前exists中单独触发一个事件
            Stat stat = zk.exists(PATH, event -> {
                System.out.println(event.getType() + "--->" + event.getPath());
                try {
                    zk.exists(event.getPath(),true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            //触发Watcher事件
            System.out.println("执行setData方法");
            Stat stat2 = zk.setData(PATH, "1".getBytes(), stat.getVersion());

            Thread.sleep(1000);

            System.out.println("执行delete方法");
            zk.delete(PATH,stat2.getVersion());
            zk.close();
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}

