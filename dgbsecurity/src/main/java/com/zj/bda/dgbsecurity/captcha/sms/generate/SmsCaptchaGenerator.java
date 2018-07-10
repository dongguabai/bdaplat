package com.zj.bda.dgbsecurity.captcha.sms.generate;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.grace.bean.CaptchaBean;
import com.zj.bda.dgbsecurity.captcha.grace.generate.ICaptchaGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成
 * @author Dongguabai
 * @date 2018-07-10 10:42
 */
public class SmsCaptchaGenerator implements ICaptchaGenerator{

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    public CaptchaBean generate(ServletWebRequest request) {
        String captchaCode = RandomStringUtils.randomNumeric(dgbSecurityProperties.getCaptcha().getSms().getCodeCount());
        return new CaptchaBean(captchaCode,dgbSecurityProperties.getCaptcha().getSms().getExpireSeconds());
    }
}
