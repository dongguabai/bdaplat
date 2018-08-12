package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class StringUtil {

    public static String ifNullReturn(String candidate,String re){
        return candidate==null?re:candidate;
    }

    public static String ifNullReturnEmpty(String candidate){
        return ifNullReturn(candidate,StringUtils.EMPTY);
    }

    public static String decode(String str)  {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("解码出错！");
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

}
