package com.zj.bda.dgbsecurity.authentication.fail;

import com.zj.bda.common.unspecific.util.WebUtil;
import com.zj.bda.common.web.constant.enums.ResponseEnum;
import com.zj.bda.dgbsecurity.grace.enums.LoginResponseTypeEnum;
import com.zj.bda.dgbsecurity.grace.properties.DgbSecurityProperties;
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
            WebUtil.responseErrorJson(response,null, HttpStatus.INTERNAL_SERVER_ERROR, ResponseEnum.ERROR_LOGIN.getMessage(),ResponseEnum.ERROR_LOGIN.getCode());
            return;
        }
        //Todo 配置自定义失败跳转页
        super.onAuthenticationFailure(request,response,exception);
    }
}
