package wm.dgb.security.support.authorization.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import wm.dgb.security.grace.util.AntPathMatcherUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dongguabai
 * @date 2018/8/3 17:09
 */
public class DefaultRbacAuthorizeHandler implements RbacAuthorizeHandler{
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        boolean hasPermission = false;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
            String username =  ((UserDetails) principal).getUsername();
            //Todo 查询用户是否有这个权限  用户->角色->资源（菜单、按钮。。。url）
            List<String> urls = new ArrayList<>();
            String uri = request.getRequestURI();
            for (String url : urls) {
                if (AntPathMatcherUtil.match(url,uri)){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
