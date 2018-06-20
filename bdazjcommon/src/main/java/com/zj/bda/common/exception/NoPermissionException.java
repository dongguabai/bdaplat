package com.zj.bda.common.exception;

/**
 * 权限不足
 * Created by Dongguabai on 2018-06-10.
 */
public class NoPermissionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoPermissionException() {
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
