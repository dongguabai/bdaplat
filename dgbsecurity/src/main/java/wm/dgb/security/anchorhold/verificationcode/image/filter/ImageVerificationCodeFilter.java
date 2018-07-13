package wm.dgb.security.anchorhold.verificationcode.image.filter;

import com.zj.bda.common.unspecific.util.SpringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import wm.dgb.security.anchorhold.verificationcode.exception.VerificationCodeException;
import wm.dgb.security.anchorhold.verificationcode.image.bean.ImageVerificationCodeBean;
import wm.dgb.security.anchorhold.verificationcode.image.gain.ImageVerificationCodeGainController;
import wm.dgb.security.support.properties.DgbSecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码Filter---保证只被调用一次
 *
 * @author Dongguabai
 * @date 2018-07-13 23:29
 */
public class ImageVerificationCodeFilter extends OncePerRequestFilter {

    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals(SpringUtil.getBean(DgbSecurityProperties.class).getBrowser().getLoginAction(), request.getRequestURI()) && StringUtils.equalsIgnoreCase("post", request.getMethod())) {
            try {
                validate(new ServletWebRequest(request,response));
            }catch (VerificationCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest responseAndRequest) throws ServletRequestBindingException {
        ImageVerificationCodeBean codeInSession = (ImageVerificationCodeBean) sessionStrategy.getAttribute(responseAndRequest,
                ImageVerificationCodeGainController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(responseAndRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new VerificationCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new VerificationCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(responseAndRequest, ImageVerificationCodeGainController.SESSION_KEY);
            throw new VerificationCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new VerificationCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(responseAndRequest, ImageVerificationCodeGainController.SESSION_KEY);
    }
}
