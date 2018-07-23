package wm.dgb.security.environment.browser.controller;

import com.zj.bda.common.web.enums.ResponseEnum;
import com.zj.bda.common.web.util.ResponseUtil;
import com.zj.bda.common.web.vo.ResponseVO;
import wm.dgb.security.grace.constant.DgbSecurityConstant;
import wm.dgb.security.grace.properties.DgbSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一判断身份认证请求
 * @author Dongguabai
 * @date 2018-07-11 16:42
 */
@RestController
public class BrowserSecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseVO requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String redirectUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(redirectUrl, DgbSecurityConstant.END_WITH_HTML)){
                redirectStrategy.sendRedirect(request,response,dgbSecurityProperties.getBrowser().getLoginPage());
                return null;
            }
        }
        return ResponseUtil.error(ResponseEnum.ERROR_UNLOGIN);
    }

    /**
     * session失效处理，现在改用handler处理
     * @return
     */
  /*  @RequestMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseVO sessionInvalid(){
        return ResponseUtil.error(ResponseEnum.ERROR_ACCOUNT_EXPIRE);
    }*/
}
