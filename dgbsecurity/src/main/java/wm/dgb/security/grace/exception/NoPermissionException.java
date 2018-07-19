package wm.dgb.security.grace.exception;

import lombok.NoArgsConstructor;

/**
 * 权限不足
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class NoPermissionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

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
