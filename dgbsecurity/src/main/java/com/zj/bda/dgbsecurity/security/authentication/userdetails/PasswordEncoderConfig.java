package com.zj.bda.dgbsecurity.security.authentication.userdetails;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Dongguabai
 * @date 2018-07-11 15:18
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*public class MyPasswordEncoder implements PasswordEncoder{

        @Override
        public String encode(CharSequence rawPassword) {
            // TODO Auto-generated method stub
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            // TODO Auto-generated method stub
            return encodedPassword.equals(rawPassword.toString());
        }

    }*/

}
