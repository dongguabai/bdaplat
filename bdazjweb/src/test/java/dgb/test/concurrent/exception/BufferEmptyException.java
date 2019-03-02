package dgb.test.concurrent.exception;

import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor
public class BufferEmptyException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BufferEmptyException(String message) {
        super(message);
    }

    public BufferEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BufferEmptyException(Throwable cause) {
        super(cause);
    }

    public BufferEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
