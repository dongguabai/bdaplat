package wm.dgb.security.environment.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过期处理策略
 * @author Dongguabai
 * @date 2018-07-15 19:08
 */
public class BrowserInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    /**
     * @param invalidSessionUrl
     */
    public BrowserInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }
}
