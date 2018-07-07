package com.zj.bda.dgbsecurity.captcha.graphical.filter;

import com.google.common.collect.Sets;
import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.browser.authentication.fail.IdentityCheckFailureHandler;
import com.zj.bda.dgbsecurity.captcha.graphical.exception.GraphicVerificationCodeException;
import com.zj.bda.dgbsecurity.captcha.graphical.helper.ValidateGraphicVerificationCodeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 保证每次只被调用一次
 * @author Dongguabai
 * @date 2018-07-07 12:14
 */
@Component
public class GraphicVerificationCodeFilter extends OncePerRequestFilter implements InitializingBean{

    @Autowired
    private IdentityCheckFailureHandler identityCheckFailureHandler;

    @Autowired
    private ValidateGraphicVerificationCodeHelper validateGraphicVerificationCodeHelper;

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Set<String> urls = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Boolean isNeedValidateGraphicVerificationCode = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                isNeedValidateGraphicVerificationCode = true;
                break;
            }
        }
        if (isNeedValidateGraphicVerificationCode){
            try {
                validateGraphicVerificationCodeHelper.validateGraphicVerificationCode(request);
            }catch (GraphicVerificationCodeException e){
                identityCheckFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urls = Sets.newHashSet(StringUtils.splitByWholeSeparatorPreserveAllTokens(dgbSecurityProperties.getCaptcha().getGraphic().getCheckUrl(),","));
        urls.add(dgbSecurityProperties.getBrowser().getLoginAction());
    }
}
