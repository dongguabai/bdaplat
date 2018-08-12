package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;

import java.lang.reflect.Method;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AopUtil {

    /**
     * 获取当前方法对象
     * @param joinPoint
     * @return
     */
    public static Method getTargetMethodByJoinPoint(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        return methodSignature.getMethod();
    }

    /**
     * 获取代理对象原始对象类型，没有代理对象就返回本身
     * @param candidate
     * @return
     */
    public static Class<?> ultimateTargetClass(Object candidate) {
        return AopProxyUtils.ultimateTargetClass(candidate);
    }

    /**
     * 获取代理对象原始对象
     * @param candidate
     * @return
     */
    public static Object getSingletonTarget(Object candidate) {
        return AopProxyUtils.getSingletonTarget(candidate);
    }

}
