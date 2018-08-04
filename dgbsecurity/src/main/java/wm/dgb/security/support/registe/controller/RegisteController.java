package wm.dgb.security.support.registe.controller;

import com.zj.bda.common.web.util.ResponseUtil;
import com.zj.bda.common.web.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Dongguabai
 * @date 2018/8/5 1:44
 */
@Controller
public class RegisteController {

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @PostMapping("/registe")
    public ResponseVO regist(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        //todo 密码加密存储
        String encodePassword = PasswordEncoder.encode(password);
        return ResponseUtil.success();
    }
}
