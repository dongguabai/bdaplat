package com.zj.bda.dgbsecurity.captcha.graphical.bean;

import com.zj.bda.dgbsecurity.captcha.grace.bean.CaptchaBean;
import lombok.Getter;
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
public class GraphicalCaptchaBean extends CaptchaBean {

    /**
     * 展示图片
     */
    private BufferedImage image;

    public GraphicalCaptchaBean(BufferedImage image, String code, long expireSeconds) {
        super(code,expireSeconds);
        this.image = image;
    }

    public GraphicalCaptchaBean(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public GraphicalCaptchaBean(){
        super();
    }

}
