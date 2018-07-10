package com.zj.bda.dgbsecurity.captcha.graphical.helper;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.grace.process.ICaptchaProcessorTemplete;
import com.zj.bda.dgbsecurity.captcha.graphical.bean.GraphicalCaptchaBean;
import com.zj.bda.dgbsecurity.captcha.graphical.exception.GraphicCaptchsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验验证码
 * @author Dongguabai
 * @date 2018-07-07 1:46
 */
@Component
public class ValidateCaptchaHelper {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private static final String GRAPHIC_CAPTCHA_SESSION_KEY = ICaptchaProcessorTemplete.CAPTCHA_SESSION_KEY_PREFIX+"graphic";
    private static final String SMS_CAPTCHA_SESSION_KEY = ICaptchaProcessorTemplete.CAPTCHA_SESSION_KEY_PREFIX+"sms";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 校验图形验证码
     * @param request
     */
    public void validateGraphicalVerificationCode(HttpServletRequest request){
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        GraphicalCaptchaBean graphicalCaptchaBean = (GraphicalCaptchaBean)sessionStrategy.getAttribute(servletWebRequest, GRAPHIC_CAPTCHA_SESSION_KEY);
        String inputCode = request.getParameter(dgbSecurityProperties.getCaptcha().getGraphic().getInputImageCodeName());
        if (StringUtils.isBlank(inputCode)){
            throw new GraphicCaptchsException("验证码不能为空！");
        }
        if (graphicalCaptchaBean ==null){
            throw new GraphicCaptchsException("验证码不存在！");
        }
        if (graphicalCaptchaBean.isExpried()){
            removeCaptchaSession(servletWebRequest,GRAPHIC_CAPTCHA_SESSION_KEY);
            throw new GraphicCaptchsException("验证码已过期！");
        }
        if(!StringUtils.equals(inputCode, graphicalCaptchaBean.getCode())){
            throw new GraphicCaptchsException("验证码输入错误！");
        }
        removeCaptchaSession(servletWebRequest,GRAPHIC_CAPTCHA_SESSION_KEY);
    }

    private void removeCaptchaSession(ServletWebRequest servletWebRequest,String sessionKey) {
        sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
    }

}
