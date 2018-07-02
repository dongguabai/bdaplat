package com.zj.bda.common.unspecific.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by Dongguabai on 2018-06-24 23:50
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
