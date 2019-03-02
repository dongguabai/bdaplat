package com.zj.bda.common.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zj.bda.common.cache.helper.GuaCacheHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认使用的缓存
 * @author Dongguabai
 * @date 2018/8/5 21:05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class LocalCache {

    //LRU算法
    private static LoadingCache<String,Object> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            //.expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, Object>() {
                //默认的数据加载实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载.
                @Override
                public Object load(String s) throws Exception {
                    return null;
                }
            });

    public static void put(String key,String value){
        localCache.put(key,value);
    }

    public static Object get(String key){
      return GuaCacheHelper.getIfPresent(localCache,key);
    }
}
