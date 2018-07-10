package com.zj.bda.dgbsecurity.captcha.grace.config;

import com.zj.bda.common.sms.ISmsSender;
import com.zj.bda.dgbsecurity.captcha.grace.generate.ICaptchaGenerator;
import com.zj.bda.dgbsecurity.captcha.graphical.generate.GraphicalCaptchaGenerator;
import com.zj.bda.dgbsecurity.captcha.sms.generate.SmsCaptchaGenerator;
import com.zj.bda.dgbsecurity.captcha.sms.send.SmsCaptchaSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dongguabai
 * @date 2018-07-08 1:47
 */
@Configuration
public class CaptchaConfig {

    @Bean
    @ConditionalOnMissingBean(name = "graphicalCaptchaGenerator")
    public ICaptchaGenerator graphicalCaptchaGenerator(){
        GraphicalCaptchaGenerator graphicalCaptchaGenerator = new GraphicalCaptchaGenerator();
        return graphicalCaptchaGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCaptchaGenerator")
    public ICaptchaGenerator smsCaptchaGenerator(){
        return new SmsCaptchaGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCaptchaSender")
    public ISmsSender smsCaptchaSender(){
        return new SmsCaptchaSender();
    }
}
