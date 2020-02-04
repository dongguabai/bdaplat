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

    /**
     *
     *
     *          * count 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
     *          * letters true,生成的字符串可以包括字母字符
     *          * numbers true,生成的字符串可以包含数字字符
                String random = RandomStringUtils.random(15, true, false);
     *          System.out.println(random);
     *
     *
     *          * 创建一个随机字符串，其长度是指定的字符数。
     *          * 将从所有字符集中选择字符
     *
                 *random =RandomStringUtils.random(22);
                 *System.out.println(random);
     *
     *
     *          * 创建一个随机字符串，其长度是指定的字符数。
     *          * 字符将从字符串指定的字符集中选择，不能为空。如果NULL，则使用所有字符集。
     *
     *          random =RandomStringUtils.random(15,"abcdefgABCDEFG123456789");
     *          System.out.println(random);这里写代码片
     *
     *
     *               /**
     *          * 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
     *          * count:计算创建的随机字符长度
     *          * start:字符集在开始时的位置
     *          * end:字符集在结束前的位置，必须大于65
     *          * letters true,生成的字符串可以包括字母字符
     *          * numbers true,生成的字符串可以包含数字字符
     *          *
     *          random =RandomStringUtils.random(1009,5,129,true,true);这里写代码片
     *
     *
     *               /**
     *          * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z的选择）。
     *          * count:创建随机字符串的长度
                 *random =RandomStringUtils.randomAlphabetic(15);
     *
     *
     *       /**
     *          * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z的选择）。
     *          * minLengthInclusive ：要生成的字符串的包含最小长度
     *          * maxLengthExclusive ：要生成的字符串的包含最大长度
     *          random =RandomStringUtils.randomAlphabetic(2,15);这里写代码片
     *
     *
     *               /**
     *          * 创建一个随机字符串，其长度是指定的字符数，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
     *          * count ：创建的随机数长度
     *          random =RandomStringUtils.randomAlphanumeric(15);这里写代码片
     *
     *                  /**
     *          * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
     *          * minLengthInclusive ：要生成的字符串的包含最小长度
     *          * maxLengthExclusive ：要生成的字符串的包含最大长度
     *          *
     *          random =RandomStringUtils.randomAlphanumeric(5,68);这里写代码片
     *
     *
     *                  /**
     *          * 创建一个随机字符串，其长度是指定的字符数，字符将从ASCII值介于32到126之间的字符集中选择（包括）
     *          * count:随机字符串的长度
     *          random =RandomStringUtils.randomAscii(15);
     *
     *
     *                  /**
     *          * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从ASCII值介于32到126之间的字符集中选择（包括）
     *          * minLengthInclusive ：要生成的字符串的包含最小长度
     *          * maxLengthExclusive ：要生成的字符串的包含最大长度
     *              random =RandomStringUtils.randomAscii(15,30);这里写代码片
     *
     *
     *                   /**
     *          * 创建一个随机字符串，其长度是指定的字符数，将从数字字符集中选择字符。
     *          * count:生成随机数的长度
     *          random =RandomStringUtils.randomNumeric(15);
     *
     *               /**
     *          * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,将从数字字符集中选择字符.
     *          * minLengthInclusive, 要生成的字符串的包含最小长度
     *          * maxLengthExclusive 要生成的字符串的包含最大长度
     *          random =RandomStringUtils.randomNumeric(15,20);
     *
     */
}
