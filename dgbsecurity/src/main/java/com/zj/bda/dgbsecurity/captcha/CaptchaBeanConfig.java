package com.zj.bda.dgbsecurity.captcha;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.graphical.generator.GraphicVerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dongguabai
 * @date 2018-07-08 1:47
 */
@Configuration
public class CaptchaBeanConfig {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(CaptchaGenerator.class)
    public CaptchaGenerator graphicVerificationCodeGenerator(){
        GraphicVerificationCodeGenerator graphicVerificationCodeGenerator = new GraphicVerificationCodeGenerator();
        return graphicVerificationCodeGenerator;
    }
}
