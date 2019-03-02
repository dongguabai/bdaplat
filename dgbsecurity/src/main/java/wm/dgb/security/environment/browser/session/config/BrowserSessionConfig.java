package wm.dgb.security.environment.browser.session.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import wm.dgb.security.environment.browser.session.BrowserExpiredSessionStrategy;
import wm.dgb.security.environment.browser.session.BrowserInvalidSessionStrategy;
import wm.dgb.security.grace.properties.DgbSecurityProperties;

/**
 * @author Dongguabai
 * @date 2018-07-15 20:40
 */
@Configuration
public class BrowserSessionConfig {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new BrowserInvalidSessionStrategy(dgbSecurityProperties.getBrowser().getSession().getDestinationUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new BrowserExpiredSessionStrategy(dgbSecurityProperties.getBrowser().getSession().getDestinationUrl());
    }
}
