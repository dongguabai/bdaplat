package wm.dgb.security.support.authorization.rbac.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wm.dgb.security.support.authorization.rbac.DefaultRbacAuthorizeHandler;
import wm.dgb.security.support.authorization.rbac.RbacAuthorizeHandler;

/**
 * @author Dongguabai
 * @date 2018/8/3 17:14
 */
@Configuration
public class RbacConfig {

    @Bean
    @ConditionalOnMissingBean(RbacAuthorizeHandler.class)
    public RbacAuthorizeHandler rbacAuthorizeHandler(){
        return new DefaultRbacAuthorizeHandler();
    }
}
