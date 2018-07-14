package wm.dgb.security.support.verificationcode.image.generator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import wm.dgb.security.support.verificationcode.grace.generator.IVerificationCodeGenerator;
import wm.dgb.security.support.verificationcode.image.bean.ImageVerificationCodeBean;
import wm.dgb.security.support.verificationcode.image.properties.ImageVerificationCodeProperties;
import wm.dgb.security.grace.properties.DgbSecurityProperties;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图形验证码生成器
 * @author Dongguabai
 * @date 2018-07-14 10:59
 */
public class ImageVerificationCodeGenerator implements IVerificationCodeGenerator{

    @Getter
    @Setter
    private DgbSecurityProperties dgbSecurityProperties;

    @Override
    public ImageVerificationCodeBean generate(ServletWebRequest request) {
        ImageVerificationCodeProperties imageCode = dgbSecurityProperties.getCode().getImageCode();
        //图片长
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",imageCode.getWidth());
        //图片高
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",imageCode.getHeight());
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
        for (int i = 0; i < imageCode.getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageVerificationCodeBean(image, sRand, imageCode.getExpireIn());
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
