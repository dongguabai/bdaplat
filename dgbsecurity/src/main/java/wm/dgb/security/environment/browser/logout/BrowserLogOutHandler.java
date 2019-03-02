package wm.dgb.security.environment.browser.logout;

import com.zj.bda.common.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dongguabai
 * @date 2018-07-15 22:34
 */
public class BrowserLogOutHandler implements LogoutSuccessHandler{

    private String logoutSuccessUrl;

    public BrowserLogOutHandler(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
    }

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (StringUtils.isBlank(logoutSuccessUrl)){
            WebUtil.responseOkJson(response,null,"退出成功！");
            return;
        }
        Assert.isTrue(UrlUtils.isValidRedirectUrl(logoutSuccessUrl), "url must start with '/' or with 'http(s)'");
        redirectStrategy.sendRedirect(request,response,logoutSuccessUrl);
    }
}
