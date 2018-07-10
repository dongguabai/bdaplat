package com.zj.bda.dgbsecurity.captcha.sms.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 短信验证码properties，默认6位
 * @author Dongguabai
 * @date 2018-07-07 1:54
 */
@Getter
@Setter
public class SmsCaptchaProperties {

    private int codeCount = 6;
    private long expireSeconds = 60;
    /**
     * 获取短信验证码的url
     */
    private String getSmsUrl = "/captcha/sms";

    private String phoneNumberParam="phoneNumberParam";

}
