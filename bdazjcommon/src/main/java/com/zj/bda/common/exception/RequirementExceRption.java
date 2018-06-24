package com.zj.bda.common.exception;

/**条件过滤异常
 * Created by Dongguabai on 2018-06-10.
 */
public class RequirementExceRption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequirementExceRption() {
	}

	public RequirementExceRption(String message) {
		super(message);
	}

	public RequirementExceRption(String message, Throwable cause) {
		super(message, cause);
	}

	public RequirementExceRption(Throwable cause) {
		super(cause);
	}

	public RequirementExceRption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
