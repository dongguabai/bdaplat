package wm.dgb.security.anchorhold.authentication.afterauthentication;

import com.zj.bda.common.unspecific.util.WebUtil;
import wm.dgb.security.support.properties.DgbSecurityProperties;
import wm.dgb.security.anchorhold.authentication.afterauthentication.enums.AfterAuthenticationResponseTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (AfterAuthenticationResponseTypeEnum.JSON.equals(dgbSecurityProperties.getBrowser().getResponseType())) {
            WebUtil.responseOkJson(response, null, "登陆成功！");
            return;
        }
        //默认继续成功跳转，无需更改
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
