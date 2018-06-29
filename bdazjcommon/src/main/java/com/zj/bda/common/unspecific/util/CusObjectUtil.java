package com.zj.bda.common.unspecific.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Dongguabai on 2018-06-25 9:38
 */
public class CusObjectUtil {

    public static Boolean isContainsNull(Object...objs){
        RequirementUtil.notNull(objs);
        return ArrayUtils.contains(objs, null);
    }

    private CusObjectUtil(){
    }
}
