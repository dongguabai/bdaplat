package com.zj.bda.dgbsecurity.captcha.graphical.filter;

import com.google.common.collect.Sets;
import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.browser.authentication.usernamepassword.fail.IdentityCheckFailureHandler;
import com.zj.bda.dgbsecurity.captcha.graphical.exception.GraphicCaptchsException;
import com.zj.bda.dgbsecurity.captcha.graphical.helper.ValidateCaptchaHelper;
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
public class GraphicCaptchaFilter extends OncePerRequestFilter implements InitializingBean{

    @Autowired
    private IdentityCheckFailureHandler identityCheckFailureHandler;

    @Autowired
    private ValidateCaptchaHelper validateCaptchaHelper;

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 需校验验证码的urls
     */
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
                validateCaptchaHelper.validateGraphicalVerificationCode(request);
            }catch (GraphicCaptchsException e){
                identityCheckFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urls = Sets.newHashSet(StringUtils.splitByWholeSeparatorPreserveAllTokens(dgbSecurityProperties.getCaptcha().getGraphic().getLoginAction(),","));
        urls.add(dgbSecurityProperties.getBrowser().getLoginAction());
    }
}
