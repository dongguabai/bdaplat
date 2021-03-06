package com.zj.bda.common.exception;

import lombok.NoArgsConstructor;

/**
 * 非法参数
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class InvalidParameterException extends RuntimeException{
    private static final long serialVersionUID = 1L;

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
