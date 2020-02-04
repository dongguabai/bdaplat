package com.zj.bda.common.util;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JBeanUtils {

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * 
     * @param type
     *            要转化的类型
     * @param map
     *            包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("unchecked")
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        BeanUtils.populate(obj, map);

        /**
         * 将null string转为空串
         * @date 2014-12-22
         * 
         */
//        ConvertUtilsBean convertUtils = new ConvertUtilsBean();
//        convertUtils.register(new Converter() {
//            public Object convert(Class type, Object value) {
//                if (value == null)
//                    return "";
//                return value;
//            }
//        }, String.class);
//        BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtils,
//                new PropertyUtilsBean());
//        beanUtils.populate(obj, map);

        return obj;
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     * 
     * @param bean
     *            要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("unchecked")
    public static Map convertBean(Object bean) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将对象集合转为集合map
     * 
     * @describe：TODO
     * @param beans
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @time:2014年9月3日上午10:37:32
     */
    @SuppressWarnings("unchecked")
    public static List<Map> convertBeans(List<Object> beans)
            throws IllegalAccessException, InvocationTargetException,
            IntrospectionException {
        List<Map> maps = new ArrayList<Map>();
        for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
            Object bean = (Object) iterator.next();
            maps.add(convertBean(bean));
        }
        return maps;
    }

    /**
     * 将对多个Map转对对象集合返回
     * 
     * @describe：TODO
     * @param type
     * @param maps
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @time:2014年9月3日上午10:40:00
     */
    @SuppressWarnings("unchecked")
    public static List<Object> convertMaps(Class type, List<Map> maps)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        List<Object> beans = new ArrayList<Object>();
        for (Map map : maps) {
            beans.add(convertMap(type, map));
        }
        return beans;
    }

    /**
     * 对象复制
     * 
     * @describe：TODO
     * @param toBean
     *            目标对象
     * @param fromBean
     *            对象来源
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @time:2014年9月3日上午11:47:45
     */
    public static Object copyProperties(Object toBean, Object fromBean)
            throws IllegalAccessException, InvocationTargetException {
        if (fromBean == null) {
            return null;
        }
        BeanUtils.copyProperties(toBean, fromBean);
        return toBean;
    }

    /**
     * 对象复制(将给定的对象转化为给定的Class 类型对象并返回)
     * 
     * @describe：TODO
     * @param toClassBean
     * @param fromBean
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @time:2014年9月3日下午12:05:23
     */
    public static Object copyProperties(Class toClassBean, Object fromBean)
            throws IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException {
        if (fromBean == null) {
            return null;
        }
        Object toBean = Class.forName(toClassBean.getCanonicalName())
                .newInstance();
        return copyProperties(toBean, fromBean);
    }

    /**
     * 将给定的对象集合转换为指定的类对象集合
     * 
     * @describe：TODO
     * @param toClassBean
     *            类 类型
     * @param beans
     *            对象集合
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @time:2014年9月3日下午12:33:24
     */
    public static List copyProperties(Class toClassBean, List beans)
            throws IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException {
        List list = new ArrayList();
        for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            list.add(copyProperties(toClassBean, object));
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        //User user = new User();
        // System.out.println(convertBean(user));
        //GpGroup gp = (GpGroup) copyProperties(GpGroup.class, user);
        // System.out.println(gp);
    }

}
