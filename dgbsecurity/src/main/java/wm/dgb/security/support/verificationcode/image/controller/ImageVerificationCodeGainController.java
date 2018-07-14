package wm.dgb.security.support.verificationcode.image.controller;

import com.zj.bda.common.restrict.annotation.LocalLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;
import wm.dgb.security.support.verificationcode.grace.generator.IVerificationCodeGenerator;
import wm.dgb.security.support.verificationcode.image.bean.ImageVerificationCodeBean;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dongguabai
 * @date 2018-07-13 22:30
 */
@Controller
public class ImageVerificationCodeGainController {
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private IVerificationCodeGenerator imageVerificationCodeGenerator;

    /**
     * 获取图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/image")
    @LocalLock(key = "createImageCode")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageVerificationCodeBean imageCode = imageVerificationCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
