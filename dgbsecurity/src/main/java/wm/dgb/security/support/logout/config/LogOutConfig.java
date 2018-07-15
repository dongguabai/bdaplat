package wm.dgb.security.support.logout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import wm.dgb.security.environment.browser.logout.BrowserLogOutHandler;
import wm.dgb.security.grace.properties.DgbSecurityProperties;

/**
 * @author Dongguabai
 * @date 2018-07-15 22:34
 */
@Configuration
public class LogOutConfig {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new BrowserLogOutHandler(dgbSecurityProperties.getBrowser().getLogOut().getLogoutSuccessUrl());
    }
}
