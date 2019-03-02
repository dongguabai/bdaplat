package com.zj.bda.common.datastructure;

import lombok.ToString;

/**
 * @author Dongguabai
 * @date 2018/9/13 9:20
 */
@ToString
public class ArrayQueue<E> implements Queue<E>{

    private MyArrayList<E> array;

    public ArrayQueue(int capacity){
        array = new MyArrayList<>(capacity);
    }

    public ArrayQueue(){
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
    public void enqueue(E e){
        array.addLast(e);
    }

    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    @Override
    public E getFront(){
        return array.getFirst();
    }

}
