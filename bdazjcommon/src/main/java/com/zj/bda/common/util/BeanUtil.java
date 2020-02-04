package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018-07-24 13:45
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {

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


    //map转javaBean
    public static <T>  T mapTobean(Map<String,Object> beanMap, Class<T> beanType) throws Exception {
        T obj = beanType.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String  propertyName = pd.getName();//属性名
            Object propertyValue = beanMap.get(propertyName);//属性值
            pd.getWriteMethod().invoke(obj,propertyValue);
        }
        return obj;
    }

    //javaBean转Map
    public static Map<String,Object> beanToMap2(Object bean) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(),Object.class);//获取Bean中属性名和属性值
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();//获取属性描述对象数组
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();//属性名
            Object propertyValue = pd.getReadMethod().invoke(bean);//调用该属性的get方法
            map.put(propertyName, propertyValue);
        }
        return map;
    }
}
