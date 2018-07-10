package com.zj.bda.dgbsecurity.captcha.grace.generate;

import com.zj.bda.dgbsecurity.captcha.grace.bean.CaptchaBean;
import org.springframework.web.context.request.ServletWebRequest;

/**验证码生成器
 * 图形验证码如需特定实现：graphicCaptchaGenerator
 * 短信验证码如需特定实现：smsCaptchaGenerator
 * @author Dongguabai
 * @date 2018-07-08 1:42
 */
public interface ICaptchaGenerator {

    /**
     * 生成验证码
     * @param request
     * @return
     */
    CaptchaBean generate(ServletWebRequest request);
}
