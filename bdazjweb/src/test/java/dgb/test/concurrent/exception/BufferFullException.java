package dgb.test.concurrent.exception;

import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class BufferFullException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BufferFullException(String message) {
        super(message);
    }

    public BufferFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BufferFullException(Throwable cause) {
        super(cause);
    }

    public BufferFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
