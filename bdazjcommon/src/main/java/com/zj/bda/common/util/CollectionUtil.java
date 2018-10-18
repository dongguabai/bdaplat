package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.bag.HashBag;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Dongguabai
 * @date 2018/9/19 1:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionUtil {

    /**
     * 获取集合中相同的元素
     *
     * @param coll
     * @param e
     * @param <E>
     * @return
     */
    public static <E> int getElementCount(Collection<E> coll, E e) {
        Bag bag = new HashBag(coll);
        return bag.getCount("a");
    }

    /**
     * 忘记了  应该是比较交集
     *
     * @param c1
     * @param c2
     * @return
     */
    public static boolean disjoint(Collection<?> c1, Collection<?> c2) {
        return Collections.disjoint(c1, c2);
    }

    /**
     * 获取集合下一个元素
     * @param set
     * @param <E>
     * @return
     */
    public static <E> E getNextElementFromSet(Collection<E> set) {
        if (CollectionUtils.isEmpty(set)){
            return null;
        }
        return set.iterator().next();
    }
}
