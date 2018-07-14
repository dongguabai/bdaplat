package wm.dgb.security.support.verificationcode.grace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wm.dgb.security.support.verificationcode.grace.generator.IVerificationCodeGenerator;
import wm.dgb.security.support.verificationcode.image.generator.ImageVerificationCodeGenerator;
import wm.dgb.security.grace.properties.DgbSecurityProperties;

/**
 * @author Dongguabai
 * @date 2018-07-14 11:12
 */
@Configuration
public class VerificationCodeBeanConfig {

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageVerificationCodeGenerator")
    public IVerificationCodeGenerator imageVerificationCodeGenerator(){
        ImageVerificationCodeGenerator imageVerificationCodeGenerator = new ImageVerificationCodeGenerator();
        imageVerificationCodeGenerator.setDgbSecurityProperties(dgbSecurityProperties);
        return imageVerificationCodeGenerator;
    }
}
