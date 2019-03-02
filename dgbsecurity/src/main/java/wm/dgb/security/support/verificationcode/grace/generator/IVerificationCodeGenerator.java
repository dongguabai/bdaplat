package wm.dgb.security.support.verificationcode.grace.generator;

import org.springframework.web.context.request.ServletWebRequest;
import wm.dgb.security.support.verificationcode.image.bean.ImageVerificationCodeBean;

/**
 * 验证码生成器
 * @author Dongguabai
 * @date 2018-07-14 10:54
 */
public interface IVerificationCodeGenerator {

    /**
     * 生成图形验证码
     *
     * @param request
     * @return
     */
    ImageVerificationCodeBean generate(ServletWebRequest request);
}
