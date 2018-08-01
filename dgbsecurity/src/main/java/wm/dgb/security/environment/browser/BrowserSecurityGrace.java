package wm.dgb.security.environment.browser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import wm.dgb.security.grace.properties.DgbSecurityProperties;
import wm.dgb.security.support.authentication.afterauthentication.DgbAuthenticationFailureHandler;
import wm.dgb.security.support.authentication.afterauthentication.DgbAuthenticationSuccessHandler;
import wm.dgb.security.support.authorization.grace.AuthorizeConfigManager;
import wm.dgb.security.support.verificationcode.image.filter.ImageVerificationCodeFilter;

/**
 * @author Dongguabai
 * @date 2018-07-11 13:58
 */
@Component
public class BrowserSecurityGrace extends WebSecurityConfigurerAdapter{

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Autowired
    private DgbAuthenticationSuccessHandler dgbAuthenticationSuccessHandler;

    @Autowired
    private DgbAuthenticationFailureHandler dgbAuthenticationFailureHandler;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UserDetailsService userDetailsObtainImpl;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private AccessDeniedHandler accessDeniedServletHandler;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //装配验证码Filter
        ImageVerificationCodeFilter imageVerificationCodeFilter = new ImageVerificationCodeFilter();
        imageVerificationCodeFilter.setAuthenticationFailureHandler(dgbAuthenticationFailureHandler);
        imageVerificationCodeFilter.setDgbSecurityProperties(dgbSecurityProperties);
        imageVerificationCodeFilter.afterPropertiesSet();

        http
            .addFilterBefore(imageVerificationCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()

            /* -----这里由 authorizeConfigManager 代替
                //直接放行的url
            .antMatchers(dgbSecurityProperties.getAllowedPaths()).permitAll()
            //只有拥有ADMIN角色才能访问/test，也可以对方法进行限制，简单权限这么使用，这里只是测试
            //.antMatchers(HttpMethod.GET,"/test/c2").hasRole("ADMIN")
            //任何请求
            .anyRequest()
            //都需要身份认证
            .authenticated()
            */
            .and()

            //指定认证方式为表单登陆
            .formLogin()
                //需要校验登陆至
                .loginPage("/authentication/require")
                //登陆action
                .loginProcessingUrl(dgbSecurityProperties.getBrowser().getLoginAction())
                .successHandler(dgbAuthenticationSuccessHandler)
                .failureHandler(dgbAuthenticationFailureHandler)
            .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(dgbSecurityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsObtainImpl)
                .and()
            .logout()
                //退出登录action
                .logoutUrl(dgbSecurityProperties.getBrowser().getLogOut().getAction())
                //退出成功后跳转的页面GET
                //.logoutSuccessUrl("/test")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                //下面可以让退出登陆可以使用GET，不用携带CSRF令牌，官方建议使用POST请求退出登陆，并携带CRSF令牌。
                //.logoutRequestMatcher(new AntPathRequestMatcher(dgbSecurityProperties.getBrowser().getLogOut().getAction()))
                .and()
            .sessionManagement()
                //session过期处理
                .invalidSessionStrategy(invalidSessionStrategy)
                //也可以使用Controller
                //.invalidSessionUrl("/session/invalid")
                //session并发处理
                .maximumSessions(dgbSecurityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(dgbSecurityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and().and()
            .csrf()
                //忽略的
                //.ignoringAntMatchers()
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                .requireCsrfProtectionMatcher(httpServletRequest -> {
                    //POST非幂等性，其他放行
                    boolean notPost = !StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "POST");
                    if (notPost){
                        return false;
                    }
                    for (String crsf : dgbSecurityProperties.getBrowser().getCsrf()) {
                        if (pathMatcher.match(crsf,httpServletRequest.getRequestURI())){
                            return false;
                        }
                    }
                    return true;
                })
                .and()
            //设置允许js来源（csp）
           /* .headers()
                .contentSecurityPolicy("script-src http://code.jquery.com/")*/
           //X-Frame-Options，相同域名才是允许的。
           .headers().frameOptions().sameOrigin();
           //指定权限不足处理器
        http.exceptionHandling().accessDeniedHandler(accessDeniedServletHandler);
        authorizeConfigManager.config(http.authorizeRequests());

    }
}
