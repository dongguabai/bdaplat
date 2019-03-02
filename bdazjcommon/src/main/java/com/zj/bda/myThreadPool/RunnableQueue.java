package com.zj.bda.myThreadPool;

/**
 * Runnable 任务队列，主要用于缓存提交到线程池中的任务
 *
 * @author Dongguabai
 * @date 2018/11/29 17:29
 */
public interface RunnableQueue {

    //当有新的任务进来时首先会 offer 到队列中
    void offer(Runnable runnable);

    //工作线程通过 take 方法获取 Runnable
    Runnable take();

    //获取当前队列 Runnable 的数量
    int size();

}
