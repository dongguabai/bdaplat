package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * DES加解密工具类
 * @author Dongguabai
 * @date 2018-07-19 13:59
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DesUtil {
    /** 默认key */
    public final static String KEY = "ScAKC0XhadTHT3Al0QIDAQAB";

    /**
     * DES加密
     *
     * @author : chenssy
     * @date : 2016年5月20日 下午5:51:37
     *
     * @param data
     * 				待加密字符串
     * @param key
     * 				校验位
     * @return
     */
    public static String encrypt(String data,String key) {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }

    /**
     * DES解密
     *
     * @author : chenssy
     * @date : 2016年5月20日 下午5:52:23
     *
     * @param cryptData
     * 						待解密密文
     * @param key
     * 						校验位
     * @return
     */
    public static String decrypt(String cryptData,String key) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
            // 把字符串解码为字节数组，并解密
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }


    public static void main(String[] args) {
        System.out.println("url::"+encrypt("jdbc:oracle:thin:@127.0.0.1:1521:xe",KEY));
        System.out.println("username::"+encrypt("zjbdatag",KEY));
        System.out.println("password::"+encrypt("admin",KEY));
    }
}