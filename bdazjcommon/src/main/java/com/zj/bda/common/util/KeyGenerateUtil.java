package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.n3r.idworker.Sid;

import java.util.Random;

/**
 * 主键生成工具类
 * @author Dongguabai
 * @date 2018/7/27 10:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyGenerateUtil {

    /**
     * 一天最大毫秒86400000，最大占用27比特
     * 27+10+11=48位 最大值281474976710655(15字)，YK0XXHZ827(10字)
     * 6位(YYMMDD)+15位，共21位
     *
     * @return 固定21位数字字符串
     */
    public static String get21Num() {
       return Sid.next();
    }


    /**
     * 返回固定16位的字母数字混编的字符串。
     * @return 固定16位的字母数字混编的字符串
     */
    public static String get16LetterAndNum() {
        return Sid.nextShort();
    }


    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String ge19Num() {
        Integer number = new Random().nextInt(900000) + 100000;
        return String.valueOf(System.currentTimeMillis()) + number;
    }

}
