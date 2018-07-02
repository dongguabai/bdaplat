package com.zj.bda.common.unspecific.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        Method method = null;
        try {
            method =  clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            log.error("get Method failure");
            throw new RuntimeException(e);
        }
        return method;
    }

}
