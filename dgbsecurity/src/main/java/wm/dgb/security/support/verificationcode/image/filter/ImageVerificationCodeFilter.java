package wm.dgb.security.support.verificationcode.image.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import wm.dgb.security.support.verificationcode.grace.exception.VerificationCodeException;
import wm.dgb.security.support.verificationcode.image.bean.ImageVerificationCodeBean;
import wm.dgb.security.support.verificationcode.image.controller.ImageVerificationCodeGainController;
import wm.dgb.security.grace.properties.DgbSecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 图形验证码Filter---保证只被调用一次
 *
 * @author Dongguabai
 * @date 2018-07-13 23:29
 */
public class ImageVerificationCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Setter
    @Getter
    private Set<String> urls;

    @Getter
    @Setter
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Boolean isNeedValidate = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                isNeedValidate = true;
                break;
            }
        }
        if (isNeedValidate) {
            try {
                validate(new ServletWebRequest(request, response));
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest responseAndRequest) throws ServletRequestBindingException {
        ImageVerificationCodeBean imageVerificationCodeBean = (ImageVerificationCodeBean) sessionStrategy.getAttribute(responseAndRequest, ImageVerificationCodeGainController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(responseAndRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new VerificationCodeException("验证码的值不能为空");
        }

        if (imageVerificationCodeBean == null) {
            throw new VerificationCodeException("验证码不存在");
        }

        if (imageVerificationCodeBean.isExpried()) {
            sessionStrategy.removeAttribute(responseAndRequest, ImageVerificationCodeGainController.SESSION_KEY);
            throw new VerificationCodeException("验证码已过期");
        }

        if (!StringUtils.equals(imageVerificationCodeBean.getCode(), codeInRequest)) {
            throw new VerificationCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(responseAndRequest, ImageVerificationCodeGainController.SESSION_KEY);
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = dgbSecurityProperties.getCode().getImageCode().getUrls();
        urls = new HashSet(Arrays.asList(configUrls));
        //登录请求必加
        urls.add(dgbSecurityProperties.getBrowser().getLoginAction());
    }
}
