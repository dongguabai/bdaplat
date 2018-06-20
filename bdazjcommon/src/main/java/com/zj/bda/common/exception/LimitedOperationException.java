package com.zj.bda.common.exception;

/**
 * Created by Dongguabai on 2018-06-14.
 */
public class LimitedOperationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public LimitedOperationException() {
    }

    public LimitedOperationException(String message) {
        super(message);
    }

    public LimitedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitedOperationException(Throwable cause) {
        super(cause);
    }

    public LimitedOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
