package com.zj.bda.dgbsecurity.browser.authentication.identitycheck.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UserDetails构造器
 * @author Dongguabai
 * @date 2018-07-03 10:22
 */
@Component
public class UserDetailsConstructor {

    /**
     * 注册的时候使用此类将密码进行加密存入数据库
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * --由于SpringBoot升级到2.0，Security升级到5这个地方需要进行修改，密码前面需要加上{passEndoder}，即:
     * public static PasswordEncoder createDelegatingPasswordEncoder() {
     String encodingId = "bcrypt";
     Map<String, PasswordEncoder> encoders = new HashMap<>();
     encoders.put(encodingId, new BCryptPasswordEncoder());
     encoders.put("ldap", new LdapShaPasswordEncoder());
     encoders.put("MD4", new Md4PasswordEncoder());
     encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
     encoders.put("noop", NoOpPasswordEncoder.getInstance());
     encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
     encoders.put("scrypt", new SCryptPasswordEncoder());
     encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
     encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
     encoders.put("sha256", new StandardPasswordEncoder());

     return new DelegatingPasswordEncoder(encodingId, encoders);
     }

     --这几个值以后应该都是从数据库里面使用的

     * public User(String username, String password, boolean enabled,
     boolean accountNonExpired, boolean credentialsNonExpired,
     boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
     enable：账户可用
     accountNonExpired：账户没有过期
     credentialsNonExpired：密码没有过期
     accountNonLocked：账户没有被锁定
     */

    public User constructUser(String username){
        //ToDo  从数据库中获取相应参数，封装成User


        String password = passwordEncoder.encode("admin");
        System.out.println("password-----:"+password);
        User user = new User(username, password, true,
                true, true,
                true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }

}
