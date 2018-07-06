package com.zj.bda.dgbsecurity.browser.grace.properties;

import com.zj.bda.dgbsecurity.browser.grace.enums.LoginResponseTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dongguabai
 * @date 2018-07-04 22:13
 */
@Getter
@Setter
public class BrowserSecurityProperties {

    private String loginPage = "/dgb.html";
    private String loginAction = "/login";
    private String loginUrl = "/authentication/require";
    private String loginSuccessPage;
    private LoginResponseTypeEnum loginResponseTypeEnum = LoginResponseTypeEnum.JSON;
}
