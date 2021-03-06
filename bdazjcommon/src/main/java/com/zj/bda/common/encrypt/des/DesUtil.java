package com.zj.bda.common.encrypt.des;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * DES加解密工具类
 *
 * @author Dongguabai
 * @date 2018-07-19 13:59
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DesUtil {
    /**
     * DES加密
     *
     * @param data 待加密字符串
     * @param descEnum  校验位
     * @return
     */
    public static String encrypt(String data, DescEnum descEnum) {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(descEnum.getKey().getBytes());
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
     * @param cryptData 待解密密文
     * @param descEnum  校验位
     * @return
     */
    public static String decrypt(String cryptData, DescEnum descEnum) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(descEnum.getKey().getBytes());
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

        System.out.println("url::" + encrypt("jdbc:oracle:thin:@127.0.0.1:1521:xe", DescEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("username::" + encrypt("zjbdatag", DescEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("password::" + encrypt("admin", DescEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("------------===========");
        System.out.println("url::" + encrypt("jdbc:oracle:thin:@127.0.0.1:1521:xe", DescEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("username::" + encrypt("zjbdatag", DescEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("password::" + encrypt("admin", DescEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("------------===========");
        System.out.println("url::" + encrypt("jdbc:oracle:thin:@127.0.0.1:1521:xe", DescEnum.CSRF_TOKEN_KEY));
        System.out.println("username::" + encrypt("zjbdatag", DescEnum.CSRF_TOKEN_KEY));
        System.out.println("password::" + encrypt("admin", DescEnum.CSRF_TOKEN_KEY));
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum DescEnum {
        /**
         * 数据库properties加密key
         */
        DATABASE_PROPERTIES_KEY("ScAKC0XhadTHT3Al0QIDAQAB"),
        USERNAME_PASSWORD_LOGIN_KEY("ShAKC0XhadAHT3ll0QIDAQAB"),
        CSRF_TOKEN_KEY("18072382613026947072ABCK");

        private String key;
    }
}
