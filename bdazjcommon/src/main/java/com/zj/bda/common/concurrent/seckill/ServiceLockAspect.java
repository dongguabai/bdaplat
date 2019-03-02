package com.zj.bda.common.concurrent.seckill;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguabai
 * @date 2018/8/5 11:59
 */
@Component
@Scope
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE-1)
@Slf4j
public class ServiceLockAspect {

    private static Lock lock = new ReentrantLock(true);

    @Pointcut("execution(public * *(..)) && @annotation(com.zj.bda.common.concurrent.seckill.Servicelock)")
    public void serviceLockAspect() {

    }

    @Around("serviceLockAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        lock.lock();
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("ServiceLockAspect出现异常：{}",joinPoint);
            e.printStackTrace();
            throw new RuntimeException();
        } finally{
            lock.unlock();
        }
        return obj;
    }
}
