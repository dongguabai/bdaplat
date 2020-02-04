package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.list.TreeList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018/9/19 1:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionUtil {

    /**
     * 获取集合中某个相同元素的个数
     *
     * @param c
     * @param o
     * @param <E>
     * @return
     */
    public static <E> int frequency(Collection<?> c, Object o) {
        return Collections.frequency(c, o);
    }


    /**
     * java统计List集合中每个元素出现的次数
     * 例如frequencyOfListElements(["111","111","222"])
     * ->
     * 则返回Map {"111"=2,"222"=1}
     *
     * @param items
     * @return Map<String   ,   Integer>
     * @author wuqx
     */
    public static Map<String, Integer> frequencyMap(List<String> items) {
        if (items == null || items.size() == 0) {
            return null;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String temp : items) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return map;
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
    public static <T> List notUnionAll(List<T> notUnionList, List<T> list) {
        HashSet<T> set = new HashSet<>(notUnionList);
        set.addAll(list);
        set.removeAll(notUnionList);
        return new ArrayList(set);
    }


    /**
     * 获取两个集合的交集
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> List retainAll(List<T> list1, List<T> list2) {
        TreeList treeList = new TreeList(list1);
        treeList.retainAll(list2);
        return new ArrayList(treeList);
    }

    /**
     * 获取集合下一个元素
     *
     * @param set
     * @param <E>
     * @return
     */
    public static <E> E getNextElementFromSet(Collection<E> set) {
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        return set.iterator().next();
    }

    /**
     * 返回一个不可变的List
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list);
    }


    /**
     * public static void main(String[] args) {
     * 		Set<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5, 6);
     * 		Set<Integer> sets2 = Sets.newHashSet(3, 4, 5, 6, 7, 8, 9);
     * 		// 交集
     * 		System.out.println("交集为：");
     * 		SetView<Integer> intersection = Sets.intersection(sets, sets2);
     * 		for (Integer temp : intersection) {
     * 			System.out.println(temp);
     *                }
     * 		// 差集
     * 		System.out.println("差集为：");
     * 		SetView<Integer> diff = Sets.difference(sets, sets2);
     * 		for (Integer temp : diff) {
     * 			System.out.println(temp);
     *        }
     * 		// 并集
     * 		System.out.println("并集为：");
     * 		SetView<Integer> union = Sets.union(sets, sets2);
     * 		for (Integer temp : union) {
     * 			System.out.println(temp);
     *        }* 	}
     *
     *
     *
     *        union两个集合：
     *        CollectionUtils.union
     */

    /**
     * 批次处理
     *
     *     public static void main(String[] args) {
     *         List<Integer> list = new ArrayList<>();
     *         for (int i = 0; i < 61; i++) {
     *             list.add(i);
     *         }
     *
     *         int f = 20;
     *
     *         int end=0;
     *         int start=0;
     *         int size = list.size();
     *         int num = size % 30 == 0 ? (size / 30) : (size / 30 + 1);// 按每30条记录查询
     *
     *         List<Integer> a;
     *         for (int i = 1; i <= num; i++) {
     *             end=(i*30)> size ? size :(i*30);
     *             start=(i-1)*30;
     *             System.out.println(end+"--"+start);
     *             a=list.subList(start,end);
     *             System.out.println("输出数据-bbb--"+a.toString());
     *         }
     *
     *     }
     *
     * int end;
     *         int start;
     *         int size = data.size();
     *         int num = size % Constants.DEFAULT_BATCH_SIZE == 0 ? (size / Constants.DEFAULT_BATCH_SIZE) : (size / Constants.DEFAULT_BATCH_SIZE + 1);
     *         List<OfflineExam> temp;
     *         for (int i = 1; i <= num; i++) {
     *             end=(i*Constants.DEFAULT_BATCH_SIZE)> size ? size :(i*Constants.DEFAULT_BATCH_SIZE);
     *             start=(i-1)*Constants.DEFAULT_BATCH_SIZE;
     *             temp=data.subList(start,end);
     *             offlineExamMapper.insertOfflineExamList(temp, currentLogin.getUserId(), new Date(), batchNo, currentLogin.getRegion());
     *         }
     *
     *分页处理
     *  qbmInMiliao.sort((qb1,qb2)->qb2.getCreateDate().compareTo(qb1.getCreateDate()));
     *         int size = qbmInMiliao.size();
     *         int fromIndex = pageSize * (pageNum - 1);
     *         int toIndex = fromIndex + pageSize;
     *         if (toIndex >= size) {
     *             toIndex = size;
     *         }
     *         List<QuestionBank> questionBanks = qbmInMiliao.subList(fromIndex, toIndex);
     *
     */




    /**
     * 将集合按照没 pageSize进行分组
     * @param srcList
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> srcList, int pageSize) {
        List<List<T>> outList = new ArrayList<>();
        int start = 0;
        int end = pageSize ;
        for (;start < srcList.size(); start = end, end += pageSize) {
            end = Math.min(end, srcList.size());
            outList.add(srcList.subList(start, end));
        }
        return outList;
    }





}
