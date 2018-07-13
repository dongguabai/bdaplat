package wm.dgb.security.environment.browser;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wm.dgb.security.anchorhold.authentication.afterauthentication.DgbAuthenticationFailureHandler;
import wm.dgb.security.anchorhold.authentication.afterauthentication.DgbAuthenticationSuccessHandler;
import wm.dgb.security.anchorhold.verificationcode.image.filter.ImageVerificationCodeFilter;
import wm.dgb.security.support.properties.DgbSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Dongguabai
 * @date 2018-07-11 13:58
 */
@Configuration
public class BrowserSecurityGrace extends WebSecurityConfigurerAdapter{

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Autowired
    private DgbAuthenticationSuccessHandler dgbAuthenticationSuccessHandler;

    @Autowired
    private DgbAuthenticationFailureHandler dgbAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //增加图形验证码Filter
        ImageVerificationCodeFilter imageVerificationCodeFilter = new ImageVerificationCodeFilter();
        imageVerificationCodeFilter.setAuthenticationFailureHandler(dgbAuthenticationFailureHandler);


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
            .loginProcessingUrl(dgbSecurityProperties.getBrowser().getLoginAction())
            .successHandler(dgbAuthenticationSuccessHandler)
            .failureHandler(dgbAuthenticationFailureHandler)
            .and()
            //配置认证
            .csrf().disable();



    }
}
