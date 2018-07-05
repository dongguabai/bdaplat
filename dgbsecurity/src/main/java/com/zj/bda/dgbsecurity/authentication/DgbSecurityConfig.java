package com.zj.bda.dgbsecurity.authentication;

import com.zj.bda.dgbsecurity.grace.properties.DgbSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Dongguabai
 * @date 2018-07-02 22:57
 */
@Configuration
public class DgbSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Autowired
    private AuthenticationSuccessHandler identityCheckSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler identityCheckFailureHandler;

    @Value("${dgb.security.allowedPath}")
    private String[] allowedPaths;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用表单登陆
        http.authorizeRequests()
                //直接放行的请求
                .antMatchers(allowedPaths).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()      //loginProcessingUrl("/authentication")  is default  .defaultSuccessUrl("/test.html")
                .loginPage(dgbSecurityProperties.getBrowser().getLoginUrl())
                .loginProcessingUrl(dgbSecurityProperties.getBrowser().getLoginAction())
                //成功处理器
                .successHandler(identityCheckSuccessHandler)
                //失败处理器
                .failureHandler(identityCheckFailureHandler)
                //.defaultSuccessUrl(dgbSecurityProperties.getBrowser().getLoginSuccessPage()) //默认登陆成功页面
                .and()
                .csrf().disable();
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
