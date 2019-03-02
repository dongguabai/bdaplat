package dgb.test;

import com.zj.bda.common.util.SpringUtil;
import com.zj.bda.web.controller.test.Test01Controller;

/**
 * @author Dongguabai
 * @date 2018/10/7 21:26
 */
public class MainTest {

    public static void main(String[] args) {
        Test01Controller bean = SpringUtil.getBean(Test01Controller.class);
    }
}
