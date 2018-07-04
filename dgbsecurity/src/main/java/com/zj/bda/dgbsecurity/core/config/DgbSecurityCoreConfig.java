package com.zj.bda.dgbsecurity.core.config;

import com.zj.bda.dgbsecurity.core.properties.DgbSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dongguabai
 * @date 2018-07-04 22:17
 */
@Configuration
@EnableConfigurationProperties(DgbSecurityProperties.class)
public class DgbSecurityCoreConfig {
}
