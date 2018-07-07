package com.zj.bda.dgbsecurity.captcha;

import com.zj.bda.dgbsecurity.captcha.graphical.bean.ImageCodeBean;
import org.springframework.web.context.request.ServletWebRequest;

/**验证码生成器
 * @author Dongguabai
 * @date 2018-07-08 1:42
 */
public interface CaptchaGenerator {

    /**
     * 生成验证码
     * @param request
     * @return
     */
    ImageCodeBean generateCodeAndPic(ServletWebRequest request);
}
