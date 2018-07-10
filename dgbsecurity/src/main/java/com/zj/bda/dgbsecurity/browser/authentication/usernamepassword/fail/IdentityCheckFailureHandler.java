package com.zj.bda.dgbsecurity.browser.authentication.usernamepassword.fail;

import com.zj.bda.common.unspecific.util.WebUtil;
import com.zj.bda.common.web.constant.enums.ResponseEnum;
import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.browser.grace.enums.LoginResponseTypeEnum;
import com.zj.bda.dgbsecurity.captcha.graphical.exception.GraphicCaptchsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败Handler
 * @author Dongguabai
 * @date 2018-07-05 19:33
 */
@Component("identityCheckFailureHandler")
public class IdentityCheckFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (LoginResponseTypeEnum.JSON.equals(dgbSecurityProperties.getBrowser().getLoginResponseTypeEnum())) {
            //注意httpstatus
            if (exception instanceof GraphicCaptchsException){
                WebUtil.responseErrorJson(response,null, HttpStatus.OK, exception.getMessage(),ResponseEnum.ERROR_GRAPHIC_CAPTCHA_VALIDATE.getCode());
                return;
            }
            WebUtil.responseErrorJson(response,null, HttpStatus.OK, ResponseEnum.ERROR_LOGIN.getMessage(),ResponseEnum.ERROR_LOGIN.getCode());
            return;
        }
        //Todo 配置自定义失败跳转页
        super.onAuthenticationFailure(request,response,exception);
    }
}
