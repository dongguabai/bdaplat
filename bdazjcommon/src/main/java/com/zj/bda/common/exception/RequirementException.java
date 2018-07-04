package com.zj.bda.common.exception;

import lombok.NoArgsConstructor;

/**
 * 条件过滤异常
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class RequirementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequirementException(String message) {
		super(message);
	}

	public RequirementException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequirementException(Throwable cause) {
		super(cause);
	}

	public RequirementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
