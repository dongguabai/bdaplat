package wm.dgb.security.support.verificationcode.image.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 图形验证码配置
 * @author Dongguabai
 * @date 2018-07-14 9:57
 */
@Setter
@Getter
public class ImageVerificationCodeProperties {

    /**
     * 宽（请求传>配置文件）
     */
    private int width = 67;
    /**
     * 高（请求传>配置文件）
     */
    private int height = 23;
    /**
     * 长度（配置文件）
     */
    private int length = 4;
    /**
     * 过期时间（秒）（配置文件）
     */
    private int expireIn = 60;
    /**
     * 需要验证码拦截的url
     */
    private String[] urls;
}
