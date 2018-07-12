package com.zj.bda.dgbsecurity.security.authentication.afterauthentication;

import com.zj.bda.common.unspecific.util.WebUtil;
import com.zj.bda.common.web.constant.enums.ResponseEnum;
import com.zj.bda.dgbsecurity.support.properties.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.security.authentication.afterauthentication.enums.AfterAuthenticationResponseTypeEnum;
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
@Component("dgbAuthenticationFailureHandler")
public class DgbAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (AfterAuthenticationResponseTypeEnum.JSON.equals(dgbSecurityProperties.getBrowser().getResponseType())) {
            //注意httpstatus
            //增加验证码异常处理，优先判断验证码
           /* if (exception instanceof GraphicCaptchsException){
                WebUtil.responseErrorJson(response,null, HttpStatus.OK, exception.getMessage(),ResponseEnum.ERROR_GRAPHIC_CAPTCHA_VALIDATE.getCode());
                return;
            }*/
           //注意HttpStatus
            WebUtil.responseErrorJson(response,null, HttpStatus.OK, ResponseEnum.ERROR_LOGIN.getMessage(),ResponseEnum.ERROR_LOGIN.getCode());
            return;
        }
        //Todo 配置自定义失败跳转页
       super.onAuthenticationFailure(request,response,exception);
    }
}
