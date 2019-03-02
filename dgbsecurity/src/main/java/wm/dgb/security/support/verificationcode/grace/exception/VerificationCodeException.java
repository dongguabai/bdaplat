package wm.dgb.security.support.verificationcode.grace.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 * @author Dongguabai
 * @date 2018-07-13 23:43
 */
public class VerificationCodeException extends AuthenticationException{
    private static final long serialVersionUID = 1L;

    public VerificationCodeException(String message) {
        super(message);
    }

}
