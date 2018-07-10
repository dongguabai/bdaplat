package com.zj.bda.dgbsecurity.captcha.graphical.process;

import com.zj.bda.dgbsecurity.captcha.grace.process.impl.AbstractCaptchaProcessor;
import com.zj.bda.dgbsecurity.captcha.graphical.bean.GraphicalCaptchaBean;
import com.zj.bda.dgbsecurity.captcha.graphical.generate.GraphicalCaptchaGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 处理图形验证码
 * @author Dongguabai
 * @date 2018-07-10 14:42
 */
@Component("graphicalCaptchaProcessor")
public class GraphicalCaptchaProcessGor extends AbstractCaptchaProcessor<GraphicalCaptchaBean> {

    @Override
    protected void send(ServletWebRequest requestAndResponse, GraphicalCaptchaBean captchaBean) throws Exception {
        ImageIO.write(captchaBean.getImage(), GraphicalCaptchaGenerator.IMAGE_FORMAT_NAME, requestAndResponse.getResponse().getOutputStream());
    }
}
