package com.zj.bda.dgbsecurity.captcha.grace.properties;

import com.zj.bda.dgbsecurity.captcha.graphical.properties.GraphicalCaptchaProperties;
import com.zj.bda.dgbsecurity.captcha.sms.properties.SmsCaptchaProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * 验证码properties
 * @author Dongguabai
 * @date 2018-07-08 0:17
 */
@Getter
@Setter
public class CaptchaProperties {
    private GraphicalCaptchaProperties graphic = new GraphicalCaptchaProperties();
    private SmsCaptchaProperties sms = new SmsCaptchaProperties();
}
