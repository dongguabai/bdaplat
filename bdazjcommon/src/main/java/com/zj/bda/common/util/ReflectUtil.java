package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Dongguabai
 * @date 2018-07-01 14:59
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtil {

    /**
     * 获取类的Method
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes){
        return ReflectionUtils.findMethod(clazz,methodName,parameterTypes);
    }

    /**
     * 反射执行方法
     * @param t
     * @param methodName
     * @param parameterTypes
     * @param params
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> Object invoke(T t,String methodName,Class<?>[] parameterTypes,Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = t.getClass();
        Method method = clazz.getMethod(methodName,parameterTypes);
        method.setAccessible(true);
        return method.invoke(t,params);
    }

    public static <T> Object invoke(T t,String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = t.getClass();
        Method method = clazz.getMethod(methodName);
        method.setAccessible(true);
        return method.invoke(t);
    }
}
