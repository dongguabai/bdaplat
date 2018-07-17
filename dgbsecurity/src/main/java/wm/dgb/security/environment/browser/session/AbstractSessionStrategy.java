package wm.dgb.security.environment.browser.session;

import com.zj.bda.common.util.WebUtil;
import com.zj.bda.common.web.enums.ResponseEnum;
import com.zj.bda.common.web.helper.ResponseHelper;
import com.zj.bda.common.web.vo.ResponseVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * session处理策略
 * @author Dongguabai
 * @date 2018-07-15 18:45
 */
@Slf4j
public class AbstractSessionStrategy {
    /**
     * 跳转的url
     */
    private String destinationUrl = "/index.html";
    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 跳转前是否创建新的session
     */
    @Setter
    private boolean createNewSession = true;

    /**
     * @param invalidSessionUrl
     */
    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (createNewSession) {
            request.getSession(true);
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            targetUrl = destinationUrl;
            log.info("session失效,跳转到 {}",targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            ResponseVO result = buildResponseContent(request);
            WebUtil.responseErrorJson(response,HttpStatus.UNAUTHORIZED,result);
        }

    }

    /**
     * @param request
     * @return
     */
    protected ResponseVO buildResponseContent(HttpServletRequest request) {
        if(isConcurrency()){
            return ResponseHelper.error(ResponseEnum.ERROR_ACCOUNT_CONCURRENCY);
        }
        return ResponseHelper.error(ResponseEnum.ERROR_ACCOUNT_EXPIRE);
    }

    /**
     * session失效是否是并发导致的
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }

}
