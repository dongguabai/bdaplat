package com.zj.bda.dgbsecurity.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Dongguabai
 * @date 2018-07-02 22:57
 */
@Configuration
public class DgbSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${form.login.page}")
    private String loginPage;

    @Value("${form.login.allowedPath}")
    private String[] allowedPaths;

    @Value("${form.login.action}")
    private String loginAction;

    @Value("${form.login.success}")
    private String success;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用表单登陆
        http.formLogin()      //loginProcessingUrl("/login")  is default  .defaultSuccessUrl("/test.html")
                .loginPage(loginPage).loginProcessingUrl(loginAction).defaultSuccessUrl(success)
                .and()
                .authorizeRequests()
                //直接放行的请求
                .antMatchers(allowedPaths).permitAll()
                .anyRequest()
                .authenticated()
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
