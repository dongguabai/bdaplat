package com.zj.bda.common.concurrent.restrict;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zj.bda.common.cache.constant.enums.CacheExpireTimeEnum;
import com.zj.bda.common.cache.helper.GuaCacheHelper;
import com.zj.bda.common.exception.LimitedOperationException;
import com.zj.bda.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author Dongguabai
 * @date 2018-07-02 9:07
 */
@Aspect
@Configuration
@Order(0)
@Slf4j
public class LocalLockAspect {

    private final Cache<String, Object> LOCALLOCK_CACHES = CacheBuilder.newBuilder()
            //初始化容量
            .initialCapacity(30)
            // 最大缓存 1000 个
            .maximumSize(1000)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            // 设置写缓存后 5 秒钟过期
            .expireAfterWrite(CacheExpireTimeEnum.LOCAL_LOCK.getTime(), CacheExpireTimeEnum.LOCAL_LOCK.getTimeUnit())
            .build();

    @Around("execution(public * *(..)) && @annotation(com.zj.bda.common.concurrent.restrict.LocalLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = localLock.keyGenerate()?getKey(localLock.key(), pjp.getArgs()):getKey(localLock.key());
        if (StringUtils.isNotEmpty(key)) {
            if (GuaCacheHelper.getIfPresent(LOCALLOCK_CACHES,key) != null) {
                throw new LimitedOperationException("请勿重复请求");
            }
            // 如果是第一次请求,就将 key 当前对象压入缓存中
            LOCALLOCK_CACHES.put(key, key);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            //这个策略可以根据实际情况选择
            //LOCALLOCK_CACHES.invalidate(key);
            log.error("LocalLockAspect出现异常：{}",pjp);
            throwable.printStackTrace();
            throw new RuntimeException();
        } finally {
            //这个策略可以根据实际情况选择，这个实际上就是一次请求成功了就可以进行下一次请求了
            LOCALLOCK_CACHES.invalidate(key);
        }
    }

    /**
     * key 的生成策略,接口与实现类
     *
     * @param keyExpress 表达式
     * @param args       参数
     * @return 生成的key
     */
    private String getKey(String keyExpress, Object[] args) {
        return new StringBuilder(StringUtils.join(args, "-")).append(keyExpress).append(IpUtil.getIpAddress()).toString();
    }

    private String getKey(String keyExpress) {
        return new StringBuilder(keyExpress).append(IpUtil.getIpAddress()).toString();
    }

}
