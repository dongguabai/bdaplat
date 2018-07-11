package com.zj.bda.dgbsecurity.captcha.grace.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 验证码基类
 * @author Dongguabai
 * @date 2018-07-06 15:42
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaBean {

    /**
     * 随机数
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public CaptchaBean(String code,long expireSeconds){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSeconds);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
