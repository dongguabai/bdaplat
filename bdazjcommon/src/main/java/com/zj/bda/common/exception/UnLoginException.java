package com.zj.bda.common.exception;

import lombok.NoArgsConstructor;

/**
 * 未登陆
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class UnLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

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
