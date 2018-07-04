package com.zj.bda.common.unspecific.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CusStringUtil {

    public static String ifNullReturn(String checkedStr,String re){
        return checkedStr==null?re:checkedStr;
    }

    public static String ifNullReturnEmpty(String checkedStr){
        return ifNullReturn(checkedStr,"");
    }

}
