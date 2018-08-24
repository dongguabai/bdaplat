package com.zj.bda.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import wm.dgb.security.support.authorization.rbac.RbacAuthorizeHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试模拟实现RbacAuthorizeHandler
 * @author Dongguabai
 * @date 2018/8/3 17:17
 */
@Component
public class CustomerAuthorizeHandler implements RbacAuthorizeHandler{
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        System.out.println("自定义权限校验-----");
        return true;
    }
}
