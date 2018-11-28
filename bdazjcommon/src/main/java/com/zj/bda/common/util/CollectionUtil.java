package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.collections.list.TreeList;

import java.util.*;

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
     * 忘记了  应该是比较集合是否有交集
     *
     * @param c1
     * @param c2
     * @return
     */
    public static boolean disjoint(Collection<?> c1, Collection<?> c2) {
        return Collections.disjoint(c1, c2);
    }

    /**
     * 求两个集合之间不是交集的部分，把两个集合丢到Set中，再removeAll其中一个
     * list1中不是交集的部分
     */
    public static <T>  List notUnionAll(List<T> notUnionList,List<T> list){
        HashSet<T> set = new HashSet<>(notUnionList);
        set.addAll(list);
        set.removeAll(notUnionList);
        return new ArrayList(set);
    }


    /**
     * 获取两个集合的交集
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T>  List retainAll(List<T> list1,List<T> list2){
        TreeList treeList = new TreeList(list1);
        treeList.retainAll(list2);
        return new ArrayList(treeList);
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

    /**
     * 返回一个不可变的List
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list);
    }

}
