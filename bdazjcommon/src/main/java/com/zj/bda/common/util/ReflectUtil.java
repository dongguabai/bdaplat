package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

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

}
