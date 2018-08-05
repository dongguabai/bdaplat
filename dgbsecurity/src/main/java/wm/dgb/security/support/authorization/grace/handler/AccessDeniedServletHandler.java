package wm.dgb.security.support.authorization.grace.handler;

import com.zj.bda.common.util.WebUtil;
import com.zj.bda.common.web.ServerResponseEnum;
import com.zj.bda.common.web.ServerResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import wm.dgb.security.grace.properties.DgbSecurityProperties;
import wm.dgb.security.support.authentication.afterauthentication.enums.AfterAuthenticationResponseTypeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dongguabai
 * @date 2018/8/1 23:25
 */
@Component("accessDeniedServletHandler")
public class AccessDeniedServletHandler implements AccessDeniedHandler {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        if (AfterAuthenticationResponseTypeEnum.JSON.equals(dgbSecurityProperties.getBrowser().getResponseType())) {
            WebUtil.responseErrorJson(response, HttpStatus.FORBIDDEN, ServerResponseHelper.error(ServerResponseEnum.ERROR_NO_PERMISSION));
            return;
        }
        redirectStrategy.sendRedirect(request,response,dgbSecurityProperties.getBrowser().getNoPermissionPage());
    }
}
