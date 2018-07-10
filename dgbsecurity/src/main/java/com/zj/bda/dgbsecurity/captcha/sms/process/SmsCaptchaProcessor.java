package com.zj.bda.dgbsecurity.captcha.sms.process;

import com.zj.bda.common.sms.ISmsSender;
import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.grace.bean.CaptchaBean;
import com.zj.bda.dgbsecurity.captcha.grace.process.impl.AbstractCaptchaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理
 * @author Dongguabai
 * @date 2018-07-10 14:42
 */
@Component("smsCaptchaProcessor")
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor<CaptchaBean>{

    @Autowired
    private ISmsSender smsCaptchaSender;

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    protected void send(ServletWebRequest requestAndResponse, CaptchaBean captchaBean) throws Exception {
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(requestAndResponse.getRequest(), dgbSecurityProperties.getCaptcha().getSms().getPhoneNumberParam());
        smsCaptchaSender.sendMessage(phoneNumber,captchaBean.getCode());
    }
}
