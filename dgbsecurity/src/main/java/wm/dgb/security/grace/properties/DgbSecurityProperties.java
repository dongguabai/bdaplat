package wm.dgb.security.grace.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import wm.dgb.security.environment.browser.properties.BrowserProperties;
import wm.dgb.security.support.registe.properties.RegisteProperties;
import wm.dgb.security.support.verificationcode.grace.properties.VerificationCodeProperties;

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

    /**
     * 验证码配置
     */
    private VerificationCodeProperties code = new VerificationCodeProperties();
    /**
     * 注册配置
     */
    private RegisteProperties registe = new RegisteProperties();
}
