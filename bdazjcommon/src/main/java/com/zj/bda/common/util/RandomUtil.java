package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 *
 * @author Dongguabai
 * @date 2018-07-20 11:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtil {

    //根据随机计算50%概率：random.nextInt(2) & 1) == 0

    /**
     * commons 包里面的工具类
     * <p>
     * //产生5位长度的随机字符串，中文环境下是乱码
     * RandomStringUtils.random(5);
     * <p>
     * //使用指定的字符生成5位长度的随机字符串
     * RandomStringUtils.random(5, new char[]{'a','b','c','d','e','f', '1', '2', '3'});
     * <p>
     * //生成指定长度的字母和数字的随机组合字符串
     * RandomStringUtils.randomAlphanumeric(5);
     * <p>
     * //生成随机数字字符串
     * RandomStringUtils.randomNumeric(5);
     * <p>
     * //生成随机[a-z]字符串，包含大小写
     * RandomStringUtils.randomAlphabetic(5);
     * <p>
     * //生成从ASCII 32到126组成的随机字符串
     * RandomStringUtils.randomAscii(4)
     */

    /**
     * * 生成一个随机的布尔值
     * <p>
     * boolean flag = RandomUtils.nextBoolean();
     * System.out.println(flag);
     *
     * * 创建一个bytes随机数组
     * byte[] byt = RandomUtils.nextBytes(6);
     * System.out.println(byt);
     *
     * * 返回一个0 - Integer.MAX_VALUE的随机 整数
     * <p>
     * int intt = RandomUtils.nextInt();
     *
     * * 返回一个在指定区间内的整数
     * * startInclusive 可以返回的最小值必须是非负的
     * * endExclusive 上限（不包括）
     * intt =RandomUtils.nextInt(20,60);
     *
     * * 返回一个在区间0 - Long.MAX_VALUE的long类型的数
     * <p>
     * long lontt = RandomUtils.nextLong();
     *
     * * 返回一个在指定区间的long类型的随机数
     * * startInclusive 可以返回的最小值必须是非负的
     * * endExclusive 上限（不包括）
     * lontt =RandomUtils.nextLong(34,68);
     *
     * * 返回一个在区间0 - Double.MAX_VALUE double随机数
     * <p>
     * double dout = RandomUtils.nextDouble();
     *
     * * 返回一个在指定区间的double随机数
     * * startInclusive 可以返回的最小值必须是非负的
     * * endExclusive 上限（不包括）
     * dout =RandomUtils.nextDouble(23.0,34);
     *
     * * 返回一个在0 - Float.MAX_VALUE之间的float类型随机数
     * <p>
     * float flott = RandomUtils.nextFloat();
     *
     * * 返回一个指定区间的float类型随机数
     * * startInclusive 可以返回的最小值必须是非负的
     * * endExclusive 上限（不包括）
     * flott =RandomUtils.nextFloat(23,56);
     */


    private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_CHAR = "0123456789";

    /**
     * 获取定长的随机数，包含大小写、数字
     *
     * @param length 随机数长度
     * @return
     */
    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(ThreadLocalRandom.current().nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 获取定长的随机数，包含大小写字母
     *
     * @param length 随机数长度
     * @return
     */
    public static String generateMixString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(LETTER_CHAR.charAt(ThreadLocalRandom.current().nextInt(LETTER_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 获取定长的随机数，只包含小写字母
     *
     * @param length 随机数长度
     * @return
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 获取定长的随机数，只包含大写字母
     *
     * @param length 随机数长度
     * @return
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 获取定长的随机数，只包含数字
     *
     * @param length 随机数长度
     * @return
     */
    public static String generateNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBER_CHAR.charAt(ThreadLocalRandom.current().nextInt(NUMBER_CHAR.length())));
        }
        return sb.toString();
    }

}
