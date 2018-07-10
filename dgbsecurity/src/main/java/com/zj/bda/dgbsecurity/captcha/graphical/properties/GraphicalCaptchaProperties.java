package com.zj.bda.dgbsecurity.captcha.graphical.properties;

import com.zj.bda.dgbsecurity.captcha.sms.properties.SmsCaptchaProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * 图形验证码properties，默认4位数
 * @author Dongguabai
 * @date 2018-07-07 1:54
 */
@Getter
@Setter
public class GraphicalCaptchaProperties extends SmsCaptchaProperties{

    private int imageWidth;
    private int imageHeight;
    private String getImageUrl = "/captcha/graphical";
    private String inputImageCodeName = "imageCode";

    /**
     * 登陆提交的url
     */
    private String loginAction;

    public GraphicalCaptchaProperties(){
        setCodeCount(4);
    }

}
