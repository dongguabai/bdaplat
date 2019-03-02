package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018/10/5 23:33
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArrayUtil {

    /**
     * 获取数组中第二大的元素
     * @param numbers
     * @return
     */
    public static int secondBiggest(int numbers[]) {
        int max = 0;
        int second = 0;
        for (int number : numbers) {
            if (number > max) {
                second = max;
                max = number;
            } else if (number > second) {
                second = number;
            }
        }
        return second;
    }
}
