package wm.dgb.security.environment.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import wm.dgb.security.grace.properties.DgbSecurityProperties;
import wm.dgb.security.support.authentication.afterauthentication.DgbAuthenticationFailureHandler;
import wm.dgb.security.support.authentication.afterauthentication.DgbAuthenticationSuccessHandler;
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
            //直接放行的url
            .antMatchers(dgbSecurityProperties.getAllowedPaths()).permitAll()
            //任何请求
            .anyRequest()
            //都需要身份认证
            .authenticated()
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
                //退出成功后跳转的页面
                //.logoutSuccessUrl("/test")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
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
            .csrf().disable();

    }
}
