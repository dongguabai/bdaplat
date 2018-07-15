package wm.dgb.security.environment.browser.properties;

import lombok.Getter;
import lombok.Setter;
import wm.dgb.security.environment.browser.logout.properties.BrowserLogOutProperties;
import wm.dgb.security.environment.browser.session.properties.BrowserSessionProperties;
import wm.dgb.security.support.authentication.afterauthentication.enums.AfterAuthenticationResponseTypeEnum;

/**
 * @author Dongguabai
 * @date 2018-07-11 16:54
 */
@Getter
@Setter
public class BrowserProperties {

    /**
     * 登陆路径
     */
    private String loginPage = "/dgb.html";

    /**
     * 表单登陆路口
     */
    private String loginAction = "/authentication/form";

    /**
     * 响应类型
     */
    private AfterAuthenticationResponseTypeEnum responseType = AfterAuthenticationResponseTypeEnum.JSON;

    /**
     * 记住我时间（秒）
     */
    private int rememberMeSeconds = 3600;

    /**
     * session处理
     */
    private BrowserSessionProperties session = new BrowserSessionProperties();

    /**
     * 退出处理
     */
    private BrowserLogOutProperties logOut = new BrowserLogOutProperties();
}
