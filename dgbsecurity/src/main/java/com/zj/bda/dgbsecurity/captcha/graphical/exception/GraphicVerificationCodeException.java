package com.zj.bda.dgbsecurity.captcha.graphical.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 * @author Dongguabai
 * @date 2018-07-07 12:45
 */
public class GraphicVerificationCodeException extends AuthenticationException{

    public GraphicVerificationCodeException(String msg) {
        super(msg);
    }
}
