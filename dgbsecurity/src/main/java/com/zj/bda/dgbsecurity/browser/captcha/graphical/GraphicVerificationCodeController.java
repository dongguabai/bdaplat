package com.zj.bda.dgbsecurity.browser.captcha.graphical;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dongguabai
 * @date 2018-07-06 15:54
 */
@RestController
public class GraphicVerificationCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/captcha/graphical")
    public void generateGraphicVerificationCode(HttpServletRequest request, HttpServletResponse response){

    }
}
