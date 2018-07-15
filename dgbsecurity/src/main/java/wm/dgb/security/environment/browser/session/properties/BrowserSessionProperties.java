package wm.dgb.security.environment.browser.session.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dongguabai
 * @date 2018-07-15 18:25
 */
@Getter
@Setter
public class BrowserSessionProperties {

    private int maximumSessions = 1;

    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin = false;

    /**
     * session失效时跳转的地址
     */
    private String destinationUrl = "/index.html";

}
