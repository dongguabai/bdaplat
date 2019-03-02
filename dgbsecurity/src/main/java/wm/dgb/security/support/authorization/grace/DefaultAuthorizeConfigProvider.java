package wm.dgb.security.support.authorization.grace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import wm.dgb.security.grace.properties.DgbSecurityProperties;

/**
 * @author Dongguabai
 * @date 2018/8/1 18:00
 */
@Component
@Order(Integer.MIN_VALUE)
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider{

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(dgbSecurityProperties.getAllowedPaths()).permitAll();
    }
}
