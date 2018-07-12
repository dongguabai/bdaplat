package com.zj.bda.dgbsecurity.environment.browser.properties;

import com.zj.bda.dgbsecurity.security.authentication.afterauthentication.enums.AfterAuthenticationResponseTypeEnum;
import lombok.Getter;
import lombok.Setter;

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

}
