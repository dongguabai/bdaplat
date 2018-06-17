package com.zj.bda.web.exception;

/**未登录
 * Created by Dongguabai on 2018-06-10.
 */
public class UnLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnLoginException() {
	}

	public UnLoginException(String message) {
		super(message);
	}

	public UnLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnLoginException(Throwable cause) {
		super(cause);
	}

	public UnLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
