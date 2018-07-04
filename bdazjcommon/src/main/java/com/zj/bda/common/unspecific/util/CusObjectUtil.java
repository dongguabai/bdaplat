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

    public static Boolean isContainsNull(Object...objs){
        RequirementUtil.notNull(objs);
        return ArrayUtils.contains(objs, null);
    }

}
