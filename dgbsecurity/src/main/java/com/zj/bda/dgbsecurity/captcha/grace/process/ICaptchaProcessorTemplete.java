package com.zj.bda.dgbsecurity.captcha.grace.process;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 处理验证码
 * @author Dongguabai
 * @date 2018-07-10 14:12
 */
public interface ICaptchaProcessorTemplete {
    /**
     * 验证码放入seesion前缀
     */
     String CAPTCHA_SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CAPTCHA_";

    /**
     * 创建验证码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest requestAndResponse) throws Exception;
}
