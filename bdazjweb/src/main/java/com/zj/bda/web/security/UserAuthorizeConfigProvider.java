package com.zj.bda.web.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import wm.dgb.security.support.authorization.grace.AuthorizeConfigProvider;

/**
 * @author Dongguabai
 * @date 2018/8/1 23:01
 */
@Component
@Order(Integer.MAX_VALUE)
public class UserAuthorizeConfigProvider implements AuthorizeConfigProvider{
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
    }
}
