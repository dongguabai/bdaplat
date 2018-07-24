package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018-07-24 13:45
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CusBeanUtil {

    public static void copyProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }

    public static Map<String, String > beanToMapExcludeClass(Object bean){
        Map<String, String> describe =beanToMap(bean);
        describe.remove("class");
        return describe;
    }

    public static Map<String, String > beanToMap(Object bean) {
        try {
            return org.apache.commons.beanutils.BeanUtils.describe(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>(0);
    }
}
