package com.zj.bda.common.datastructure;

/**
 * @author Dongguabai
 * @date 2018/9/13 9:16
 */
public interface Queue<E> {

    /**
     * 入队
     * @param e
     */
    void enqueue(E e);

    /**
     * 出队
     * @return
     */
    E dequeue();

    /**
     * 查看队首的元素
     * @return
     */
    E getFront();

    /**
     * 元素个数
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();
}
