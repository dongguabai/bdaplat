package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.floor;
import static java.lang.Math.log;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class StringUtil {

    public static String defaultIfNull(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    public static String defaultString(String str) {
        return StringUtils.defaultString(str);
    }

    public static String defaultString(String str, String defaultStr) {
        return StringUtils.defaultString(str,defaultStr);
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return StringUtils.defaultIfEmpty(str,defaultStr);
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
     *
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
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        return WordUtils.capitalize(str);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String uncapitalize(String str) {
        return WordUtils.uncapitalize(str);
    }

    /**
     * 指定位置插入
     *
     * @param source
     * @param dstoffset
     * @param charSequence
     * @return
     */
    public static String insert(String source, int dstoffset, CharSequence charSequence) {
        return new StringBuilder(source).insert(dstoffset, charSequence).toString();
    }

    /**
     * 判断字符串是不是回文串
     * @param s
     * @return
     */
    public static boolean isPlalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // 预处理字符串，在两个字符之间加上#
    private static String preHandleString(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        sb.append('#');
        for(int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

    /**
     * 寻找最长回文字串
      * @param s
     * @return
     */
    public static String findLongestPlalindromeString(String s) {
        // 先预处理字符串
        String str = preHandleString(s);
        // 处理后的字串长度
        int len = str.length();
        // 右边界
        int rightSide = 0;
        // 右边界对应的回文串中心
        int rightSideCenter = 0;
        // 保存以每个字符为中心的回文长度一半（向下取整）
        int[] halfLenArr = new int[len];
        // 记录回文中心
        int center = 0;
        // 记录最长回文长度
        int longestHalf = 0;
        for(int i = 0; i < len; i++) {
            // 是否需要中心扩展
            boolean needCalc = true;
            // 如果在右边界的覆盖之内
            if(rightSide > i) {
                // 计算相对rightSideCenter的对称位置
                int leftCenter = 2 * rightSideCenter - i;
                // 根据回文性质得到的结论
                halfLenArr[i] = halfLenArr[leftCenter];
                // 如果超过了右边界，进行调整
                if(i + halfLenArr[i] > rightSide) {
                    halfLenArr[i] = rightSide - i;
                }
                // 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了
                if(i + halfLenArr[leftCenter] < rightSide) {
                    // 直接推出结论
                    needCalc = false;
                }
            }
            // 中心扩展
            if(needCalc) {
                while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
                    if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
                        halfLenArr[i]++;
                    } else {
                        break;
                    }
                }
                // 更新右边界及中心
                rightSide = i + halfLenArr[i];
                rightSideCenter = i;
                // 记录最长回文串
                if(halfLenArr[i] > longestHalf) {
                    center = i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        // 去掉之前添加的#
        StringBuffer sb = new StringBuffer();
        for(int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 是否有非法字符
     * @param string
     * @return
     */
    public static boolean isNotFilter(String string){
        if (StringUtils.isBlank(string)) {
            return true;
        }
        // String regex="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        String regex="/[^\u4e00-\u9fa5a-zA-Z0-9]/ig";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(string);
        return !match.matches();
    }

    /**
     * 格式化搜索字符串
     *
     * <pre>
     * StringUtil.keywords(null)          = null
     * StringUtil.keywords("")            = null
     * StringUtil.keywords("     ")       = null
     * StringUtil.keywords("abc")         = "%abc%"
     * StringUtil.keywords("    abc    ") = "%abc%"
     * StringUtil.keywords("  a bc  ")    = "%a%bc%"
     * </pre>
     *
     * @param keyword
     * @return
     */
    public static String keywords(String keyword) {
        keyword = StringUtils.trimToNull(keyword);
        if (keyword == null || "".equals(keyword.trim())) {
            return null;
        }
        keyword = keyword.replaceAll("\\s+", "%");
        return "%" + keyword + "%";
    }


    /**
     * 输入序号，输出对应字母
     * @param n
     * @return
     */
    private static String getString(int n) {
        char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('A' + n % 26);
            n /= 26;
        }
        return new String(buf);
    }

}
