package com.zj.bda.common.exception;

/**条件过滤异常
 * Created by Dongguabai on 2018-06-10.
 */
public class RequirementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequirementException() {
	}

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
