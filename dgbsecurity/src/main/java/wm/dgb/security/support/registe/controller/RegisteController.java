package wm.dgb.security.support.registe.controller;

import com.zj.bda.common.web.ServerResponseHelper;
import com.zj.bda.common.web.ServerResponse;
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
    public ServerResponse regist(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        //todo 密码加密存储
        String encodePassword = PasswordEncoder.encode(password);
        return ServerResponseHelper.success();
    }
}
