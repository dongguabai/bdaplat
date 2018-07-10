package com.zj.bda.dgbsecurity.captcha.grace.controller;

import com.zj.bda.dgbsecurity.captcha.grace.process.ICaptchaProcessorTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码Controller
 *
 * @author Dongguabai
 * @date 2018-07-06 15:54
 */
@RestController
public class CaptchaController {

    @Autowired
    private Map<String, ICaptchaProcessorTemplete> captchaProcessors;

    @GetMapping("/captcha/{type}")
    public void createCaptcha(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        captchaProcessors.get(type + "CaptchaProcessor").create(new ServletWebRequest(request, response));
    }

   /* public static final String CAPTCHA_SESSION_KEY = "graphic_verification_code_session_key";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    *//**
     *  图片验证码生成器
     *//*
    @Resource(name = "graphicCaptchaGenerator")
    private ICaptchaGenerator graphicCaptchaGenerator;

    *//**
     * 短信验证码生成器
     *//*
    @Resource(name = "smsCaptchaGenerator")
    private ICaptchaGenerator smsCaptchaGenerator;

    *//**
     * 短信验证码发送
     *//*
    @Autowired
    private ISmsSender smsCaptchaSender;*/

    /**
     * 图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
   /* @GetMapping("/captcha/graphical")
    public void generateGraphicCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        GraphicalCaptchaBean graphicalCaptchaBean = (GraphicalCaptchaBean)graphicCaptchaGenerator.generate(servletWebRequest);
        sessionStrategy.setAttribute(servletWebRequest,CAPTCHA_SESSION_KEY, graphicalCaptchaBean);
        ImageIO.write(graphicalCaptchaBean.getImage(), GraphicalCaptchaGenerator.IMAGE_FORMAT_NAME, response.getOutputStream());
    }*/

    /**
     * 短信验证码---->登陆
     * @param request
     * @param response
     * @throws IOException
     */
  /*  @GetMapping("/captcha/sms")
    public void generateSmsCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        CaptchaBean smsCaptchaBean = smsCaptchaGenerator.generate(servletWebRequest);
        sessionStrategy.setAttribute(servletWebRequest,CAPTCHA_SESSION_KEY,smsCaptchaBean);
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(request,"phoneNumber");
        //Todo   如有需要，进行短信发送
        smsCaptchaSender.sendMessage(phoneNumber,smsCaptchaBean.getCode());
    }*/
}
