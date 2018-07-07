package com.zj.bda.dgbsecurity.captcha.graphical.grace;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.browser.authentication.fail.IdentityCheckFailureHandler;
import com.zj.bda.dgbsecurity.captcha.graphical.helper.ValidateGraphicVerificationCodeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 保证每次只被调用一次
 * @author Dongguabai
 * @date 2018-07-07 12:14
 */
@Component
public class GraphicVerificationCodeFilter extends OncePerRequestFilter{

    @Autowired
    private IdentityCheckFailureHandler identityCheckFailureHandler;

    @Autowired
    private ValidateGraphicVerificationCodeHelper validateGraphicVerificationCodeHelper;

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private static final String ALLOWED_METHOD = "POST";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals(request.getRequestURI(),dgbSecurityProperties.getBrowser().getLoginAction()) && StringUtils.equalsIgnoreCase(ALLOWED_METHOD,request.getMethod())){
            try {
                validateGraphicVerificationCodeHelper.validateGraphicVerificationCode(request);
            }catch (GraphicVerificationCodeException e){
                identityCheckFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
