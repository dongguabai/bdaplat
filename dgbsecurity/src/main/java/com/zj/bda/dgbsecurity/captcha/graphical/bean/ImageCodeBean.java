package com.zj.bda.dgbsecurity.captcha.graphical.bean;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ImageCodeBean {

    /**
     * 展示图片
     */
    private BufferedImage image;
    /**
     * 随机数
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ImageCodeBean(BufferedImage image, String code, long expireSeconds) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSeconds);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
