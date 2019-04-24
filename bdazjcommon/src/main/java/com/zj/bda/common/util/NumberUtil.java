package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author dongguabai
 * @Description
 * @Date 创建于 2019-04-24 22:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil {

    /**
     * 判断是否为奇数（推荐）
     * @param i
     * @return
     */
    public static boolean isOdd(int i){
        return (i&1)!=0;
    }

    /**
     * 判断是否为奇数（一般推荐）
     * @param i
     * @return
     */
    public static boolean isOdd2(int i){
        //注：不要使用 i & 2 ==1; @See Java Puzzlers
        return i % 2!=0;
    }
}
