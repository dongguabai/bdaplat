package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {

    /**
     * 数组中是否包含null
     * @param objs
     * @return
     */
    public static Boolean isContainsNull(Object[] objs){
        return ArrayUtils.contains(objs, null);
    }

    public static Object ifNullReturn(Object candidate,Object re){
        return candidate==null?re:candidate;
    }

    public static Object ifNullReturnNull(Object candidate){
        return ifNullReturn(candidate,null);
    }
}
