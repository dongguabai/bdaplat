package com.zj.bda.common.concurrent.async.config;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Dongguabai
 * @date 2018-07-22 20:29
 */
@Configuration
@Slf4j
public class ThreadPoolTaskExecutorConfig  implements AsyncConfigurer {

    /**
     * url:springboot\ThreadPoolTaskExecutor
     *
     * Reject策略预定义有四种：
     (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时 RejectedExecutionException。
     (2)ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
     (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃.
     (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.

     int corePoolSize:线程池维护线程的最小数量.
     　　int maximumPoolSize:线程池维护线程的最大数量.
     　　long keepAliveTime:空闲线程的存活时间.
     　　TimeUnit unit: 时间单位,现有纳秒,微秒,毫秒,秒枚举值.
     　　BlockingQueue<Runnable> workQueue:持有等待执行的任务队列.
     　　RejectedExecutionHandler handler:
     　　用来拒绝一个任务的执行，有两种情况会发生这种情况。
     　　一是在execute方法中若addIfUnderMaximumPoolSize(command)为false，即线程池已经饱和；
     　　二是在execute方法中, 发现runState!=RUNNING || poolSize == 0,即已经shutdown,就调用ensureQueuedTaskHandled(Runnable command)，在该方法中有可能调用reject。

     ThreadPoolExecutor池子的处理流程如下：　　

     1）当池子大小小于corePoolSize就新建线程，并处理请求

     2）当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去从workQueue中取任务并处理

     3）当workQueue放不下新入的任务时，新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize就用RejectedExecutionHandler来做拒绝处理

     4）另外，当池子的线程数大于corePoolSize的时候，多余的线程会等待keepAliveTime长的时间，如果无请求可处理就自行销毁

     其会优先创建  CorePoolSiz 线程， 当继续增加线程时，先放入Queue中，当 CorePoolSiz  和 Queue 都满的时候，就增加创建新线程，当线程达到MaxPoolSize的时候，就会抛出错 误 org.springframework.core.task.TaskRejectedException

     另外MaxPoolSize的设定如果比系统支持的线程数还要大时，会抛出java.lang.OutOfMemoryError: unable to create new native thread 异常。
     */


    /**
     * Async Annotation
     * 核心线程数10：线程池创建时候初始化的线程数
     最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
     缓冲队列200：用来缓冲执行任务的队列
     允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
     线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
     线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
     *
     * 使用：@Async("asyncTaskExecutor")
     *
     * @return
     */
    @Bean
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix(generateThreadName("@AsyncTask"));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    @Bean
    public AsyncTaskExecutor mvcAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix(generateThreadName("MvcAsync"));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    private String generateThreadName(String threadName){
        return Joiner.on("").join("《", threadName, "-");
    }


    //异步注解异常处理（具体可看移动硬盘：grace\spring boot\@Async注解和多线程异步执行\springboot异步调用@Async%20-%20个人文章%20-%20SegmentFault%20思否.mhtml）
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                log.info("pppppppp"+"发生了异常");
                log.error("发生了异常,{}，方法名称:{}",throwable,method.getName());
            }
        };
    }
}
