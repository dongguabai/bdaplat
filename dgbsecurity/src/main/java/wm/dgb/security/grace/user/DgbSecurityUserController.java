package wm.dgb.security.grace.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取当前用户信息
 * @author Dongguabai
 * @date 2018-07-12 23:09
 */
@RestController
public class DgbSecurityUserController {

    /**
     * 获取Authentication
     * @param authentication
     * @return
     */
    @RequestMapping("/me/authentication")
    public Authentication getCurrentUser(Authentication authentication){
        return authentication;
    }

    /**
     * 获取
     * @param userDetails
     * @return
     */
    @RequestMapping("/me/userDetails")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

}
