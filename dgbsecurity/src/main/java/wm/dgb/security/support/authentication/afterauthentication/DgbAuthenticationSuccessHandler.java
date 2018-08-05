package wm.dgb.security.support.authentication.afterauthentication;

import com.zj.bda.common.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import wm.dgb.security.grace.properties.DgbSecurityProperties;
import wm.dgb.security.support.safe.csrf.vo.CsrfVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功Handler
 *
 * @author Dongguabai
 * @date 2018-07-05 19:33
 */
@Component("dgbAuthenticationSuccessHandler")
public class DgbAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * csrfToken session key
     */
    public static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class
            .getName().concat(".CSRF_TOKEN");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //if (AfterAuthenticationResponseTypeEnum.JSON.equals(dgbSecurityProperties.getBrowser().getResponseType())) {
            CsrfToken token = (CsrfToken)sessionStrategy.getAttribute(new ServletWebRequest(request, response), DEFAULT_CSRF_TOKEN_ATTR_NAME);
            WebUtil.responseOkJson(response, new CsrfVO(token), "登陆成功！");
            //return;
       // }
        //默认继续成功跳转，无需更改
        //super.onAuthenticationSuccess(request, response, authentication);
    }
}
