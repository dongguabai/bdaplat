package wm.dgb.security.grace.exception;

import lombok.NoArgsConstructor;

/**
 * 登陆失败
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class LoginErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginErrorException(String message) {
		super(message);
	}

	public LoginErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginErrorException(Throwable cause) {
		super(cause);
	}

	public LoginErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
