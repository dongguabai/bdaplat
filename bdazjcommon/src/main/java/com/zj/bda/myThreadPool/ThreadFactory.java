package com.zj.bda.myThreadPool;

/**
 * 线程池创建工厂
 * @author Dongguabai
 * @date 2018/11/30 10:18
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
