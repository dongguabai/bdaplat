package com.zj.bda.common.encrypt.aes;

import com.zj.bda.common.util.KeyGenerateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Dongguabai
 * @date 2018-07-25 23:44
 *
 * 本项目使用的加密方式
 */
@Slf4j
public class AesUtil {
    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param aesEnum 加密密码
     * @return
     */
    public static byte[] encryptEbc(String content, AesEcbEnum aesEnum) {
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

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param aesEnum 解密密钥
     * @return
     */
    public static byte[] decryptEbc(byte[] content, AesEcbEnum aesEnum) {
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
     *
     * @param content 要加密的字符串
     * @param aesEnum 加密的AES Key
     * @return
     */
    public static String encryptStringEbc(String content, AesEcbEnum aesEnum) {
        return parseByte2HexStr(encryptEbc(content, aesEnum));
    }

    /**
     * 字符串解密
     *
     * @param content 要解密的字符串
     * @param aesEnum 解密的AES Key
     * @return
     */
    public static String decryptStringEbc(String content, AesEcbEnum aesEnum) {
        byte[] decryptFrom = parseHexStr2Byte(content);
        byte[] decryptResult = decryptEbc(decryptFrom, aesEnum);
        return new String(decryptResult);
    }


    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0, length = hexStr.length() / 2; i < length; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = buf.length; i < length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 加密
     *
     * @param sSrc
     * @param encodingFormat
     * @param sKey           必须要16位
     * @param ivParameter    向量必须要16位
     * @return
     */
    public static String encryptCbc(String sSrc, String encodingFormat, String sKey, String ivParameter) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
            return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
        } catch (Exception e) {
            log.error("加密异常！",e);
            return null;
        }
    }

    /**
     * 加密
     *
     * @param sSrc    必须要16位
     * @param aesEnum
     * @return
     * @throws Exception
     */
    public static String encryptCbc(String sSrc, AesCbcEnum aesEnum) {
        return encryptCbc(sSrc, "utf-8", aesEnum.getKey(), aesEnum.getIvParameter());
    }

    /**
     * 解密
     *
     * @param sSrc
     * @param encodingFormat
     * @param sKey           必须要16位
     * @param ivParameter    向量必须要16位
     * @return
     * @throws Exception
     */
    public static String decryptCbc(String sSrc, String encodingFormat, String sKey, String ivParameter) {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, encodingFormat);
            return originalString;
        } catch (Exception ex) {
            log.error("解密异常！", ex);
            return null;
        }
    }

    /**
     * 解密
     *
     * @param sSrc    必须要16位
     * @param aesEnum
     * @return
     * @throws Exception
     */
    public static String decryptCbc(String sSrc, AesCbcEnum aesEnum) {
        return decryptCbc(sSrc, "utf-8", aesEnum.getKey(), aesEnum.getIvParameter());
    }

    public static void main(String[] args) {

        System.out.println(KeyGenerateUtil.get16LetterAndNum());
        System.out.println(KeyGenerateUtil.get16LetterAndNum());
        System.out.println(KeyGenerateUtil.get16LetterAndNum());

        System.out.println("url::" + encryptStringEbc("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesEcbEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("username::" + encryptStringEbc("zjbdatag", AesEcbEnum.DATABASE_PROPERTIES_KEY));
        System.out.println("password::" + encryptStringEbc("root", AesEcbEnum.DATABASE_PROPERTIES_KEY));
      /*  System.out.println("------------===========");
        System.out.println("url::" + encryptStringEbc("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesEcbEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("username::" + encryptStringEbc("zjbdatag", AesEcbEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("password::" + encryptStringEbc("root", AesEcbEnum.USERNAME_PASSWORD_LOGIN_KEY));
        System.out.println("------------===========");
        System.out.println("url::" + encryptStringEbc("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesEcbEnum.CSRF_TOKEN_KEY));
        System.out.println("username::" + encryptStringEbc("zjbdatag", AesEcbEnum.CSRF_TOKEN_KEY));
        System.out.println("password::" + encryptStringEbc("root", AesEcbEnum.CSRF_TOKEN_KEY));*/

        System.out.println("=====================CBC===================================");
        System.out.println("url::" + encryptCbc("jdbc:oracle:thin:@127.0.0.1:1521:xe", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("username::" + encryptCbc("zjbdatag", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("password::" + encryptCbc("root", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("url::" + decryptCbc("pRGiWkJc76agx0XYtZmejyt+Zu6Cgi7/r2sLvLfgX81gfO5L49l9yZXzeRJkf/Ve", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("username::" + decryptCbc("/oZAZpdTNvfQ04ZlX98cCA==", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("password::" + decryptCbc("NicMvZ3F9Ldwd6q4lfv7xQ==", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("=====================CBC  MYSQL===================================");
        System.out.println("url::" + encryptCbc("jdbc:mysql://172.16.140.142:3306/Demo1", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("username::" + encryptCbc("root", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        System.out.println("password::" + encryptCbc("root", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
       // System.out.println("url::" + decryptCbc("pRGiWkJc76agx0XYtZmejyt+Zu6Cgi7/r2sLvLfgX81gfO5L49l9yZXzeRJkf/Ve", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        //System.out.println("username::" + decryptCbc("/oZAZpdTNvfQ04ZlX98cCA==", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
        //System.out.println("password::" + decryptCbc("NicMvZ3F9Ldwd6q4lfv7xQ==", AesCbcEnum.DATABASE_PROPERTIES_KEY_IVPARAMETER));
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum AesEcbEnum {
        /**
         * 数据库properties加密key
         */
        DATABASE_PROPERTIES_KEY("ScAKC0XhadTHT3Al0QIDAQAB"),
        USERNAME_PASSWORD_LOGIN_KEY("ShAKC0XhadAHT3ll0QIDAQAB"),
        CSRF_TOKEN_KEY("18072382613026947072ABCK");

        private String key;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum AesCbcEnum {
        /**
         * 数据库properties加密key
         */
        DATABASE_PROPERTIES_KEY_IVPARAMETER("1808028W4109AFW0", "1808028XKSRTA141"),
        USERNAME_PASSWORD_LOGIN_KEY_IVPARAMETER("1808028W4109AFW2", "1808028XKSRTA140"),
        CSRF_TOKEN_KEY_IVPARAMETER("1808028W4109AFWK", "1808028XKSR9NXAW");

        private String key;
        private String ivParameter;
    }

    public static String generateAesCbcKey(){
        return KeyGenerateUtil.get16LetterAndNum();
    }

    public static String generateAesCbcIvParameter(){
        return KeyGenerateUtil.get16LetterAndNum();
    }


}
