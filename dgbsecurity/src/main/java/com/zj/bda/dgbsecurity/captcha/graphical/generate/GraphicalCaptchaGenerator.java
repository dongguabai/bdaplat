package com.zj.bda.dgbsecurity.captcha.graphical.generate;

import com.zj.bda.dgbsecurity.DgbSecurityProperties;
import com.zj.bda.dgbsecurity.captcha.grace.generate.ICaptchaGenerator;
import com.zj.bda.dgbsecurity.captcha.graphical.bean.GraphicalCaptchaBean;
import com.zj.bda.dgbsecurity.captcha.graphical.properties.GraphicalCaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成图形验证码
 * @author Dongguabai
 * @date 2018-07-07 1:46
 */
public class GraphicalCaptchaGenerator implements ICaptchaGenerator {

    /**
     * 图形验证码生成格式
     */
    public static final String IMAGE_FORMAT_NAME = "JPEG";

    @Autowired
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    public GraphicalCaptchaBean generate(ServletWebRequest request) {
        GraphicalCaptchaProperties graphic = dgbSecurityProperties.getCaptcha().getGraphic();
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",graphic.getImageWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",graphic.getImageHeight());
        int codeCount = graphic.getCodeCount();
        long expireSeconds = graphic.getExpireSeconds();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < codeCount; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new GraphicalCaptchaBean(image, sRand, expireSeconds);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
