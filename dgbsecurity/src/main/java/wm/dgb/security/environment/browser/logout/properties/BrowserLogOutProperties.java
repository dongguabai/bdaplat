package wm.dgb.security.environment.browser.logout.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dongguabai
 * @date 2018-07-15 22:38
 */
@Getter
@Setter
public class BrowserLogOutProperties {
    /**
     * 退出成功跳转页面
     */
    private String logoutSuccessUrl;

    /**
     * 退出登陆action
     */
    private String action;
}
