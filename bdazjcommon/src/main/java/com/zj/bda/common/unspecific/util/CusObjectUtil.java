package com.zj.bda.common.unspecific.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CusObjectUtil {

    /**
     * 数组中是否包含null
     * @param objs
     * @return
     */
    public static Boolean isContainsNull(Object[] objs){
        return ArrayUtils.contains(objs, null);
    }

}
