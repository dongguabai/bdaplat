package wm.dgb.security.support.authentication.userdetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息
 * @author Dongguabai
 * @date 2018-07-11 15:02
 */
@Component
@Slf4j
public class UserDetailsObtainImpl implements UserDetailsService {

    @Autowired
    private UserDetailsCheck userDetailsCheck;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("当前登陆用户名为：{}",username);
        return userDetailsCheck.constructUser(username);
    }
}
