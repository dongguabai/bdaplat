package wm.dgb.security.support.authorization.grace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Dongguabai
 * @date 2018/8/1 18:00
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager{

    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        authorizeConfigProviders.forEach(authorizeConfigProvider -> {
            authorizeConfigProvider.config(config);
        });
        config.anyRequest().authenticated();
    }
}
