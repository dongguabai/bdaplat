package com.zj.bda.dgbsecurity.browser.authentication;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.graphical.filter.GraphicVerificationCodeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author Dongguabai
 * @date 2018-07-02 22:57
 */
@Configuration
@Slf4j
public class BrowserAuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Autowired
    private AuthenticationSuccessHandler identityCheckSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler identityCheckFailureHandler;

    @Autowired
    private GraphicVerificationCodeFilter graphicVerificationCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired

    private UserDetailsService userDetailsAuthenticationService;

    @Value("${dgb.security.allowedPath}")
    private String[] allowedPaths;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    //直接放行的请求
                    .antMatchers(allowedPaths).permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .addFilterBefore(graphicVerificationCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //使用表单登陆
                .formLogin()      //loginProcessingUrl("/authentication")  is default  .defaultSuccessUrl("/test.html")
                    //被拦截进入
                    .loginPage(dgbSecurityProperties.getBrowser().getLoginUrl())
                    //处理formLogin username password
                    .loginProcessingUrl(dgbSecurityProperties.getBrowser().getLoginAction())
                    //.defaultSuccessUrl(dgbSecurityProperties.getBrowser().getLoginSuccessPage()) //默认登陆成功页面.and()
                    //成功处理器
                    .successHandler(identityCheckSuccessHandler)
                    //失败处理器
                    .failureHandler(identityCheckFailureHandler)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(dgbSecurityProperties.getBrowser()
                    .getRememberMeSeconds())
                    .userDetailsService(userDetailsAuthenticationService)

                .and()
                .csrf()
                    .disable();
    }


    /**
     * 由于SpringBoot升级到2.0，Security升级到5这个地方需要进行修改
     *
     * @return
     */
   /*@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/
}
