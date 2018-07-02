package com.zj.bda.common.unspecific.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Dongguabai on 2018-06-25 9:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CusObjectUtil {

    public static Boolean isContainsNull(Object...objs){
        RequirementUtil.notNull(objs);
        return ArrayUtils.contains(objs, null);
    }

}
