package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class StringUtil {

    public static String ifNullReturn(String candidate, String re) {
        return candidate == null ? re : candidate;
    }

    public static String ifNullReturnEmpty(String candidate) {
        return ifNullReturn(candidate, StringUtils.EMPTY);
    }

    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("解码出错！");
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String capitalize(String str){
        return WordUtils.capitalize(str);
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String uncapitalize(String str){
        return WordUtils.uncapitalize(str);
    }
}
