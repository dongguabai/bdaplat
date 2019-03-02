package wm.dgb.security.support.authorization.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dongguabai
 * @date 2018/8/3 17:08
 */
public interface RbacAuthorizeHandler {

    //todo 实现接口，自定义权限校验

    /**
     * 权限校验
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
