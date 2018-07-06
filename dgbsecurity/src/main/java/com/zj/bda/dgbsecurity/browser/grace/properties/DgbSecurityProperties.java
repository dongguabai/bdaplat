package com.zj.bda.dgbsecurity.browser.grace.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Dongguabai
 * @date 2018-07-04 22:13
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "dgb.security")
public class DgbSecurityProperties {
    BrowserSecurityProperties browser = new BrowserSecurityProperties();
}
