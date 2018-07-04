package com.zj.bda.common.exception;

import lombok.NoArgsConstructor;

/**
 * 当前路径未被找到
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
