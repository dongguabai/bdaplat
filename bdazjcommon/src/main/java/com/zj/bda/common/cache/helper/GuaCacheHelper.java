package com.zj.bda.common.cache.helper;

import com.google.common.cache.Cache;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018-07-02 9:07
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuaCacheHelper {

    public static Object getIfPresent(Cache<?, ?> cache, String key) {
        return key==null?null:cache.getIfPresent(key);
    }

}
