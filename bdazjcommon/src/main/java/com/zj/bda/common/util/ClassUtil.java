package com.zj.bda.common.util;

import org.springframework.util.ClassUtils;

/**
 * @author Dongguabai
 * @date 2018/8/1 15:18
 */
public class ClassUtil {

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        return ClassUtils.getDefaultClassLoader();
    }
}
