package wm.dgb.security.support.authentication.userdetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UserDetails校验
 *
 * @author Dongguabai
 * @date 2018-07-03 10:22
 */
@Component
@Slf4j
public class UserDetailsCheck {

    /**
     * 注册的时候使用此类将密码进行加密存入数据库
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * --由于SpringBoot升级到2.0，Security升级到5这个地方需要进行修改，密码前面需要加上{passEndoder}，即:
     * public static PasswordEncoder createDelegatingPasswordEncoder() {
     * String encodingId = "bcrypt";
     * Map<String, PasswordEncoder> encoders = new HashMap<>();
     * encoders.put(encodingId, new BCryptPasswordEncoder());
     * encoders.put("ldap", new LdapShaPasswordEncoder());
     * encoders.put("MD4", new Md4PasswordEncoder());
     * encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
     * encoders.put("noop", NoOpPasswordEncoder.getInstance());
     * encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
     * encoders.put("scrypt", new SCryptPasswordEncoder());
     * encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
     * encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
     * encoders.put("sha256", new StandardPasswordEncoder());
     * <p>
     * return new DelegatingPasswordEncoder(encodingId, encoders);
     * }
     * <p>
     * --这几个值以后应该都是从数据库里面使用的
     * <p>
     * public User(String username, String password, boolean enabled,
     * boolean accountNonExpired, boolean credentialsNonExpired,
     * boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
     * enable：账户可用
     * accountNonExpired：账户没有过期
     * credentialsNonExpired：密码没有过期
     * accountNonLocked：账户没有被锁定
     */

    public User constructUser(String username)  {

     /*   String menuList;
        try {
            menuList = ServletRequestUtils.getRequiredStringParameter(SpringUtil.getHttpServletRequest(), "menuList");
        } catch (ServletRequestBindingException e) {
            log.error("从request中获取用户名或密码错误！",e);
            throw new RuntimeException("从request中获取用户名或密码错误！");
        }
        if (StringUtils.isBlank(menuList)){
            log.error("从request中获取用户名或密码错误！");
            throw new RuntimeException("无法获取用户名和密码信息！");
        }

        //用户名密码加密
        String decrypt = DesUtil.decrypt(menuList, DesUtil.KEY);
        String[] split = StringUtils.split(decrypt, ",");
        String uname = split[0];
        String pw = split[2];
*/
        /*ToDo  从数据库中获取相应参数，封装成User，目前暂时仅用SpringSecurity自带User，后续根据需求，
        如用户表与User相差不大可以考虑扩展自定义实现UserDetails，如不行就独立创建用户表对象与User进行转换使用*/
        String password = passwordEncoder.encode("admin");
        System.out.println("password-----:" + password);
        User user = new User(username,
                            password,
                            isEnable(),
                            isAccountNonExpired(),
                            isRedentialsNonExpired(),
                            isAccountNonLocked(),
                            AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }

    /**
     * 当前账户是否可用
     * @return
     */
    private Boolean isEnable(){
        return true;
    }
    /**
     * 当前账户是否没有过期
     * @return
     */
    private Boolean isAccountNonExpired(){
        return true;
    }
    /**
     * 当前账户是否密码没有过期
     * @return
     */
    private Boolean isRedentialsNonExpired(){
        return true;
    }
    /**
     * 当前账户是否没有被锁定
     * @return
     */
    private Boolean isAccountNonLocked(){
        return true;
    }

}
