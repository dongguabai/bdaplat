package com.zj.bda.common.util;

/**
 * Created by Dongguabai on 2018-06-24 23:50
 */
public class CusStringUtil {

    public static String ifNullReturn(String checkedStr,String re){
        return checkedStr==null?re:checkedStr;
    }

    public static String ifNullReturn(String checkedStr){
        return checkedStr==null?"":checkedStr;
    }

    private CusStringUtil() {
    }
}
