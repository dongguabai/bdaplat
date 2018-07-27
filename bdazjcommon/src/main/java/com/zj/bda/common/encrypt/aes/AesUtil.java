package com.zj.bda.common.encrypt.aes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Dongguabai
 * @date 2018-07-25 23:44
 */
public class AesUtil {
    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param aesEnum  加密密码
     * @return
     */
    public static byte[] encrypt(String content, AesEnum aesEnum) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new java.security.SecureRandom(aesEnum.getKey().getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("UTF-8");
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(byteContent);
            // 加密
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param content  待解密内容
     * @param aesEnum 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, AesEnum aesEnum) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new java.security.SecureRandom(aesEnum.getKey().getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(content);
            // 加密
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串加密
     * @param content 要加密的字符串
     * @param aesEnum 加密的AES Key
     * @return
     */
    public static String encryptString(String content, AesEnum aesEnum) {
        return parseByte2HexStr( encrypt(content, aesEnum));
    }

    /**
     * 字符串解密
     * @param content 要解密的字符串
     * @param aesEnum 解密的AES Key
     * @return
     */
    public static String decryptString(String content, AesEnum aesEnum){
        byte[] decryptFrom = parseHexStr2Byte(content);
        byte[] decryptResult = decrypt(decryptFrom,aesEnum);
        return new String(decryptResult);
    }


    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0,length = hexStr.length()/2;i< length; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0,length = buf.length; i <length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum AesEnum {
        /**
         * 数据库properties加密key
         */
        DATABASE_PROPERTIES_KEY("ScAKC0XhadTHT3Al0QIDAQAB"),
        USERNAME_PASSWORD_LOGIN_KEY("ShAKC0XhadAHT3ll0QIDAQAB"),
        CSRF_TOKEN_KEY("18072382613026947072ABCK");

        private String key;
    }

    public static void main(String[] args) {

        System.out.println("url::" + encryptString("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("username::" + encryptString("zjbdatag", AesEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("password::" + encryptString("root", AesEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("------------===========");
        System.out.println("url::" + encryptString("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("username::" + encryptString("zjbdatag", AesEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("password::" + encryptString("root", AesEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("------------===========");
        System.out.println("url::" + encryptString("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesEnum.CSRF_TOKEN_KEY));
        System.out.println("username::" + encryptString("zjbdatag", AesEnum.CSRF_TOKEN_KEY));
        System.out.println("password::" + encryptString("root", AesEnum.CSRF_TOKEN_KEY));
    }

}
