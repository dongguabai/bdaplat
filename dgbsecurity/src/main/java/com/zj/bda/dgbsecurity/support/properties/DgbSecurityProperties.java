package com.zj.bda.dgbsecurity.support.properties;

import com.zj.bda.dgbsecurity.environment.browser.properties.BrowserProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Dongguabai
 * @date 2018-07-11 16:54
 */
@ConfigurationProperties(prefix = "dgb.security")
@Getter
@Setter
public class DgbSecurityProperties {

    /**
     * 允许
     */
    private String[] allowedPaths;

    /**
     * 浏览器环境
     */
    private BrowserProperties browser = new BrowserProperties();
}
