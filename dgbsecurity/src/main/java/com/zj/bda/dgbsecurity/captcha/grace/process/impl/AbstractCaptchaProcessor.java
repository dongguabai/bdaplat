package com.zj.bda.dgbsecurity.captcha.grace.process.impl;

import com.zj.bda.dgbsecurity.captcha.grace.bean.CaptchaBean;
import com.zj.bda.dgbsecurity.captcha.grace.generate.ICaptchaGenerator;
import com.zj.bda.dgbsecurity.captcha.grace.process.ICaptchaProcessorTemplete;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018-07-10 14:17
 */
public abstract class AbstractCaptchaProcessor<C extends CaptchaBean> implements ICaptchaProcessorTemplete {

    @Autowired
    public Map<String, ICaptchaGenerator> captchaGenerators;
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void create(ServletWebRequest requestAndResponse) throws Exception {
        //获取验证码
        C generate = generate(requestAndResponse);
        //保存验证码
        save(requestAndResponse, generate);
        //发送验证码
        send(requestAndResponse, generate);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ICaptchaGenerator captchaGenerator = captchaGenerators.get(type + "CaptchaGenerator");
        return (C) captchaGenerator.generate(request);
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, CAPTCHA_SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),
                validateCode);
    }

    protected abstract void send(ServletWebRequest requestAndResponse, C captchaBean) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/captcha/");
    }
}
