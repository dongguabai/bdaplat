package com.zj.bda.common.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by Dongguabai on 2018-06-15.
 */
public class AopUtil {

    /**
     * 获取当前方法对象
     * @param joinPoint
     * @return
     */
    public static Method getTargetMethodByJoinPoint(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        return targetMethod;
    }

    private AopUtil(){}
}
