package com.zj.bda.common.exception;

import lombok.NoArgsConstructor;

/**
 * 限制参数
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class LimitedOperationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

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
