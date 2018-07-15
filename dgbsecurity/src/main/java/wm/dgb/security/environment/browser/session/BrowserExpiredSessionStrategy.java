package wm.dgb.security.environment.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 并发导致
 *
 * @author Dongguabai
 * @date 2018-07-15 17:52
 */
public class BrowserExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    /**
     * @param invalidSessionUrl
     */
    public BrowserExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
