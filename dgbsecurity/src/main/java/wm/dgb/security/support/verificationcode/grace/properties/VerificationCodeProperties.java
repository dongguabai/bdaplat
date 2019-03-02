package wm.dgb.security.support.verificationcode.grace.properties;

import lombok.Getter;
import lombok.Setter;
import wm.dgb.security.support.verificationcode.image.properties.ImageVerificationCodeProperties;

/**
 * 验证码配置
 * @author Dongguabai
 * @date 2018-07-14 10:02
 */
@Getter
@Setter
public class VerificationCodeProperties {

    private ImageVerificationCodeProperties imageCode = new ImageVerificationCodeProperties();
}
