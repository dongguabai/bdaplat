package wm.dgb.security.support.verificationcode.image.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码Bean
 * @author Dongguabai
 * @date 2018-07-06 15:42
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageVerificationCodeBean {

    /**
     * 验证码图片
     */
    private BufferedImage image;

    /**
     * 随机码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * @param image
     * @param code
     * @param expireIn  过期秒数
     */
    public ImageVerificationCodeBean(BufferedImage image,String code,int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 是（true）否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
