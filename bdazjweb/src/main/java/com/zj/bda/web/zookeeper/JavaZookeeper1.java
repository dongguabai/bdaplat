package com.zj.bda.web.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguabai
 * @date 2018/10/8 15:20
 */
public class JavaZookeeper1 {

    static final String CONNECT_ADDR = "192.168.220.135:2181,192.168.220.136:2181,192.168.220.137:2181";
    static final int SESSION_OUTTIME = 2000;//ms
    /** 阻塞程序执行，等待zookeeper连接成功*/
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    static final String PATH = "/dongguabai";
    public static void main(String[] args) {
        try {
            //连接
            ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, event->{
                //如果收到了服务端的响应事件，连接成功
                if (Watcher.Event.KeeperState.SyncConnected==event.getState()){
                    connectedSemaphore.countDown();
                }
            });
            connectedSemaphore.await();
            System.out.println("连接成功！");
            //获取状态
            System.out.println(zk.getState());
            //创建节点
                //节点路径，节点内容，权限，节点类型
            zk.create(PATH, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Stat stat = new Stat();

            //获取节点
            byte[] res = zk.getData(PATH, null, stat);
            String re = new String(res);
            System.out.println("获取节点内容为："+re);

            //修改节点
                //节点路径，节点内容，version：相当于乐观锁的概念，这里保证当前修改的version是正确的
            zk.setData(PATH,"1".getBytes(),stat.getVersion());
            //获取节点
            byte[] res2 = zk.getData(PATH, null, stat);
            String re2 = new String(res2);
            System.out.println("修改之后，获取节点内容为："+re2);

            //删除节点
            zk.delete(PATH,stat.getVersion());
            //获取节点
            byte[] res3 = zk.getData(PATH, null, stat);
            String re3 = new String(res3);
            System.out.println("删除之后，获取节点内容为："+re3);

            //关闭连接
            zk.close();
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
