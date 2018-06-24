package com.zj.bda.common.util;

import com.zj.bda.common.exception.RequirementExceRption;

import java.util.Map;

/**
 * Created by Dongguabai on 2018-06-24 23:33
 */
public class RequirementUtil {

    public static final String CANNOT_NULL = "The Object can't null";
    public static final String CANNOT_EMPTY = "The Object can't empty";

    /**
     * 对象不为空,若对象为空则报异常
     *
     * @param obj     待校验对象
     * @param message 异常信息
     */
    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new RequirementExceRption(message);
        }
    }

    /**
     * 对象不为空,若对象为空则报异常
     *
     * @param obj 待校验对象
     */
    public static void notNull(Object obj) {
        notNull(obj, CANNOT_NULL);
    }

    /**
     * 对象不为空,若对象为空则报异常
     *
     * @param map
     * @param message
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        notNull(map, message);
        if (map.isEmpty()) {
            throw new RequirementExceRption(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, CANNOT_EMPTY);
    }

    private RequirementUtil() {
    }
}
