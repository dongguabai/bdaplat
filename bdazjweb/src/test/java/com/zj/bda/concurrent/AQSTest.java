package com.zj.bda.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/11 16:40
 */
public class AQSTest {
    static final class Node {
        /** Marker to indicate a node is waiting in shared mode */
        // 标识节点当前在共享模式下
        static final Node SHARED = new Node();
        /** Marker to indicate a node is waiting in exclusive mode */
        // 标识节点当前在独占模式下
        static final Node EXCLUSIVE = null;

        // ======== 下面的几个int常量是给waitStatus用的 ===========
        /** waitStatus value to indicate thread has cancelled */
        // 代表此线程取消了争抢这个锁
        static final int CANCELLED =  1;
        /** waitStatus value to indicate successor's thread needs unparking */
        // 官方的描述是，其表示当前node的后继节点对应的线程需要被唤醒
        static final int SIGNAL    = -1;
        /** waitStatus value to indicate thread is waiting on condition */
        //代表此线程根据condition在等待
        static final int CONDITION = -2;
        /**
         * waitStatus value to indicate the next acquireShared should
         * unconditionally propagate
         */
        //表名接下来五条线获取共享状态
        static final int PROPAGATE = -3;

        // 取值为上面的1、-1、-2、-3，或者0
        // 简单说如果这个值 大于0 代表此线程取消了等待，
        // 也许就是说半天抢不到锁，不抢了，ReentrantLock是可以指定timeouot的
        /**
         * 等待状态，包含如下状态：
         * 1.CANCELLED：值为1，由于在同步队列中等待的线程等待超时或者被中断，需要从同步队列中取消等待，节点进入
         */
        volatile int waitStatus;
        // 前驱节点的引用
        volatile Node prev;
        // 后继节点的引用
        volatile Node next;
        // 这个就是线程本身
        volatile Thread thread;

    }


}
