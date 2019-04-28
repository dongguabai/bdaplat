package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {

    /**
     * 数组中是否包含null
     *
     * @param objs
     * @return
     */
    public static Boolean isContainsNull(Object[] objs) {
        return ArrayUtils.contains(objs, null);
    }

    public static Object ifNullReturn(Object candidate, Object re) {
        return candidate == null ? re : candidate;
    }

    public static Object ifNullReturnNull(Object candidate) {
        return ifNullReturn(candidate, null);
    }

    public static String toString(Object obj) {
        return toString(obj,ToStringStyle.DEFAULT_STYLE);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle) {
        return ReflectionToStringBuilder.toString(obj, toStringStyle);
    }

    /**
     * 小于0，小于；大于0，大于。
     * @param var1
     * @param var2
     * @return
     */
    public static int compareToInteger(Integer var1,Integer var2){
        return var1.compareTo(var2);
    }

    /**
     * Objects
     */
    /**
     * //比较两个对象是否相等（首先比较内存地址，然后比较a.equals(b)，只要符合其中之一返回true）
     * public static boolean equals(Object a, Object b);
     *
     * //深度比较两个对象是否相等(首先比较内存地址,相同返回true;如果传入的是数组，则比较数组内的对应下标值是否相同)
     * public static boolean deepEquals(Object a, Object b);
     *
     * //返回对象的hashCode，若传入的为null，返回0
     * public static int hashCode(Object o);
     *
     * //返回传入可变参数的所有值的hashCode的总和（这里说总和有点牵强，具体参考Arrays.hashCode()方法）
     * public static int hash(Object... values);
     *
     * //返回对象的String表示，若传入null，返回null字符串
     * public static String toString(Object o)
     *
     * //返回对象的String表示，若传入null，返回默认值nullDefault
     * public static String toString(Object o, String nullDefault)
     *
     * //使用指定的比较器c 比较参数a和参数b的大小（相等返回0，a大于b返回整数，a小于b返回负数）
     * public static <T> int compare(T a, T b, Comparator<? super T> c)
     *
     * //如果传入的obj为null抛出NullPointerException,否者返回obj
     * public static <T> T requireNonNull(T obj)
     *
     * //如果传入的obj为null抛出NullPointerException并可以指定错误信息message,否者返回obj
     * public static <T> T requireNonNull(T obj, String message)
     *
     * -----------------------------以下是jdk8新增方法---------------------------
     *
     * //判断传入的obj是否为null，是返回true,否者返回false
     * public static boolean isNull(Object obj)
     *
     * //判断传入的obj是否不为null，不为空返回true,为空返回false （和isNull()方法相反）
     * public static boolean nonNull(Object obj)
     *
     * //如果传入的obj为null抛出NullPointerException并且使用参数messageSupplier指定错误信息,否者返回obj
     * public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier)
     * ---------------------
     */

}
