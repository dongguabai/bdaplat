package com.zj.bda.dgbsecurity.captcha.graphical.grace;

import com.zj.bda.dgbsecurity.captcha.graphical.bean.ImageCodeBean;
import com.zj.bda.dgbsecurity.captcha.graphical.helper.GenerateGraphicVerificationCodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dongguabai
 * @date 2018-07-06 15:54
 */
@RestController
public class GraphicVerificationCodeController {

    public static final String GRAPHIC_VERIFICATION_CODE_SESSION_KEY = "graphic_verification_code_session_key";
    private static final String IMAGE_FORMAT_NAME = "JPEG";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private GenerateGraphicVerificationCodeHelper generateGraphicVerificationCodeHelper;

    @GetMapping("/captcha/graphical")
    public void generateGraphicVerificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCodeBean imageCodeBean = generateGraphicVerificationCodeHelper.generateCodeAndPic();
        sessionStrategy.setAttribute(new ServletWebRequest(request),GRAPHIC_VERIFICATION_CODE_SESSION_KEY,imageCodeBean);
        ImageIO.write(imageCodeBean.getImage(), IMAGE_FORMAT_NAME, response.getOutputStream());
    }
}
