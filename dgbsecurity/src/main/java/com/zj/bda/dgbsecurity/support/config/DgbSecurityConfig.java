package com.zj.bda.dgbsecurity.support.config;

import com.zj.bda.dgbsecurity.support.properties.DgbSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dongguabai
 * @date 2018-07-11 16:57
 */
@Configuration
@EnableConfigurationProperties(DgbSecurityProperties.class)
public class DgbSecurityConfig {
}
