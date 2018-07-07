package com.zj.bda.dgbsecurity.captcha;

import com.zj.bda.dgbsecurity.captcha.graphical.properties.GraphicVerificationCodeProperties;
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
    private GraphicVerificationCodeProperties graphic = new GraphicVerificationCodeProperties();
}
