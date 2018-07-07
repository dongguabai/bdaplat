package com.zj.bda.dgbsecurity.captcha.graphical.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dongguabai
 * @date 2018-07-07 1:54
 */
@Getter
@Setter
public class GraphicVerificationCodeProperties {

    private int imageWidth;
    private int imageHeight;
    private int codeCount = 4;
    private long expireSeconds = 60;
    private String getImageUrl = "/captcha/graphical";

}
