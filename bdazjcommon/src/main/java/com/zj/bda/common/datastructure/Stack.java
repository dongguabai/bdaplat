package com.zj.bda.common.datastructure;

/**
 * @author Dongguabai
 * @date 2018-07-22 20:29
 */
public interface Stack<E> {

    /**
     * 栈元素个数
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入栈
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈定元素
     * @return
     */
    E peek();
}
