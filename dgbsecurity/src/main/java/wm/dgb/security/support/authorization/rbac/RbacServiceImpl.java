package wm.dgb.security.support.authorization.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dongguabai
 * @date 2018/8/1 22:42
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService{

    @Autowired
    private RbacAuthorizeHandler rbacAuthorizeHandler;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        return rbacAuthorizeHandler.hasPermission(request,authentication);
    }
}
