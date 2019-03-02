package com.zj.bda.common.util;

import com.zj.bda.common.exception.InvalidParameterException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018/8/13 9:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssertUtil {

    public static Object checkNotNull(final Object obj,String message) {
        isTrue(obj != null, message);
        return obj;
    }

    public static void isTrue(final boolean expression, final String message, final Object... values) {
        if (!expression) {
            throw new InvalidParameterException(String.format(message, values));
        }
    }
}
