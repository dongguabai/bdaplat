package com.zj.bda.dgbsecurity.captcha.graphical.helper;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.graphical.grace.GraphicVerificationCodeController;
import com.zj.bda.dgbsecurity.captcha.graphical.grace.GraphicVerificationCodeException;
import com.zj.bda.dgbsecurity.captcha.graphical.bean.ImageCodeBean;
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
public class ValidateGraphicVerificationCodeHelper {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public void validateGraphicVerificationCode(HttpServletRequest request){
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        ImageCodeBean imageCodeBean = (ImageCodeBean)sessionStrategy.getAttribute(servletWebRequest, GraphicVerificationCodeController.GRAPHIC_VERIFICATION_CODE_SESSION_KEY);
        String inputCode = request.getParameter(dgbSecurityProperties.getGraphicVerificationCode().getInputImageCodeName());
        if (StringUtils.isBlank(inputCode)){
            throw new GraphicVerificationCodeException("验证码不能为空！");
        }
        if (imageCodeBean==null){
            throw new GraphicVerificationCodeException("验证码不存在！");
        }
        if (imageCodeBean.isExpried()){
            removeGraphicVerficationCodeSession(servletWebRequest);
            throw new GraphicVerificationCodeException("验证码已过期！");
        }
        if(!StringUtils.equals(inputCode,imageCodeBean.getCode())){
            throw new GraphicVerificationCodeException("验证码输入错误！");
        }
        removeGraphicVerficationCodeSession(servletWebRequest);
    }

    private void removeGraphicVerficationCodeSession(ServletWebRequest servletWebRequest) {
        sessionStrategy.removeAttribute(servletWebRequest, GraphicVerificationCodeController.GRAPHIC_VERIFICATION_CODE_SESSION_KEY);
    }

}
