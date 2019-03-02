package wm.dgb.security.support.authorization.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dongguabai
 * @date 2018/8/1 22:41
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
