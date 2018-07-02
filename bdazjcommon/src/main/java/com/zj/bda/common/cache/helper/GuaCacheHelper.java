package com.zj.bda.common.cache.helper;

import com.google.common.cache.Cache;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Dongguabai
 * @date 2018-07-02 9:07
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuaCacheHelper {

    public Object getIfPresent(Cache<?, ?> cache, String key) {
        return key==null?null:cache.getIfPresent(key);
    }

    public static Object getStaticIfPresent(Cache<?, ?> cache, String key) {
        return key==null?null:cache.getIfPresent(key);
    }

}
