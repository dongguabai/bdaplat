package com.zj.bda.myThreadPool;

/**
 * 线程池顶级接口
 *
 * @author Dongguabai
 * @date 2018/11/29 17:16
 */
public interface ThreadPool {

    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭并销毁线程池
    void shutdown();

    //获取线程池的初始化大小
    int getInitSize();

    //获取线程池最大的线程数
    int getMaxSize();

    //获取线程池的核心线程数量
    int getCOreSize();

    //获取线程池中用于缓存任务队列的大小，即当前线程池任务数量
    int getQueueSize();

    //获取线程池中当前活跃线程的数量
    int getActiveCount();

    //查看线程池是否已经被shutdown（销毁）
    boolean isShutdown();

}
