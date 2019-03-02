package com.zj.bda.common.cache.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguabai
 * @date 2018-06-29 19:03
 */
@AllArgsConstructor
@Getter
public enum CacheExpireTimeEnum {

    /**
     *LocalLockCache expire time
     */
    LOCAL_LOCK(5, TimeUnit.SECONDS);

    private long time;
    private TimeUnit timeUnit;

}
