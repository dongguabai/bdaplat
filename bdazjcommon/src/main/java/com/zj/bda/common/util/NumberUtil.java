package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        /**
         * 计算百分比 整数
         *
         * @param divisor
         * @param dividend
         * @return
         */
        public static String proportionInt(Integer divisor, Integer dividend) {
            if (dividend == null || divisor == null)
                return null;
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format((float) divisor / (float) dividend * 100);
            if (result.indexOf(".") != -1) {
                result = Math.round(Double.parseDouble(result)) + "%";
            }
            return result;
        }

        /**
         * 计算百分比 整数
         *
         * @param divisor
         * @param dividend
         * @return
         */
        public static String proportionInt(Float divisor, Float dividend) {
            if (dividend == null || divisor == null)
                return null;
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format(divisor / dividend * 100);
            if (result.indexOf(".") != -1) {
                result = Math.round(Double.parseDouble(result)) + "%";
            }
            return result;
        }

        /**
         * 计算百分比 保留留n位小数
         *
         * @param divisor
         * @param dividend
         * @param bit
         * @return
         */
        public static String proportionDouble(Integer divisor, Integer dividend, Integer bit) {
            if (dividend == null || divisor == null || bit == null)
                return null;
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(bit);
            String result = numberFormat.format((float) divisor / (float) dividend * 100);

            return result + "%";
        }

        /**
         * 计算百分比 保留留n位小数
         *
         * @param divisor
         * @param dividend
         * @param bit
         * @return
         */
        public static String proportionDouble(Float divisor, Float dividend, Integer bit) {
            if (dividend == null || divisor == null || bit == null)
                return null;
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(bit);
            String result = numberFormat.format(divisor / dividend * 100);

            return result + "%";
        }


        /**
         * 保留n为小数
         *
         * @param d
         * @param bit
         * @return
         */
        public static Double doubleBit(Double d, Integer bit) {
            if (d == null || bit == null)
                return null;
            BigDecimal bg = new BigDecimal(d).setScale(bit, RoundingMode.DOWN);
            return bg.doubleValue();
        }


        /**
         * 保留n位小数,小数不足补0
         *
         * @param d
         * @param bit
         * @return
         */
        public static Double doubleBitWhole(Double d, Integer bit) {
            if (d == null || bit == null)
                return null;
            BigDecimal bg = new BigDecimal(d).setScale(bit, RoundingMode.DOWN);
            String dobu = bg.doubleValue() + "";
            if (dobu.indexOf(".") != -1) {
                String small = dobu.split("\\.")[1];
                for (int i = 0; i < bit - small.length(); i++) {
                    dobu += "0";
                }

            }
            return Double.parseDouble(dobu);
        }

        private final static Pattern IS_NUMBER_PATTERN = Pattern.compile("[0-9]+");

        /**
         * 判断str是否是 0-9字符串
         * @param str
         * @return
         */
        public static boolean isNumber(String str) {
            if (StringUtils.isBlank(str)) {
                return false;
            }
            Matcher isNum = IS_NUMBER_PATTERN.matcher(str);
            return isNum.matches();
        }

}
