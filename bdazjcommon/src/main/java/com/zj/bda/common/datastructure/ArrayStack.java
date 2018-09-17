package com.zj.bda.common.datastructure;

import lombok.ToString;

/**
 * 基于动态数组实现的栈
 *
 * @author Dongguabai
 * @date 2018/9/12 16:24
 */
@ToString
public class ArrayStack<E> implements Stack<E> {

    private MyArrayList<E> array;

    public ArrayStack(int capacity){
        array = new MyArrayList<>(capacity);
    }

    public ArrayStack(){
        array = new MyArrayList<>();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(E e){
        array.addLast(e);
    }

    @Override
    public E pop(){
        return array.removeLast();
    }

    @Override
    public E peek(){
        return array.getLast();
    }

    public static void main(String[] args) {
        ArrayStack<String> stringArrayStack = new ArrayStack<>();
        stringArrayStack.push("a");
        stringArrayStack.push("b");
        stringArrayStack.push("c");
        stringArrayStack.push("d");
        System.out.println(stringArrayStack);
        stringArrayStack.pop();
        System.out.println(stringArrayStack);

    }
}