package com.zj.bda.dgbsecurity.browser.authentication.usernamepassword.identitycheck;

import com.google.common.collect.ImmutableMap;
import com.zj.bda.dgbsecurity.browser.authentication.usernamepassword.identitycheck.userdetails.UserDetailsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证用户信息
 * @author Dongguabai
 * @date 2018-07-02 23:55
 */
@Component
@Slf4j
public class UserDetailsAuthenticationService implements UserDetailsService {

    @Autowired
    private UserDetailsConstructor userDetailsConstructor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String, String> map = ImmutableMap.of("admin", "admin");

        //Todo   根据用户名获取用户信息、权限（最后一个参数）

        log.info("当前登陆用户名为：{}", username);
        return userDetailsConstructor.constructUser(username);
    }
}