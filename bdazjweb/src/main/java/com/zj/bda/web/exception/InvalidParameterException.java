package com.zj.bda.web.exception;

/**
 * Created by Dongguabai on 2018-06-14.
 */
public class InvalidParameterException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidParameterException() {
    }

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }

    public InvalidParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
