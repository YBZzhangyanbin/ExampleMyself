package com.example.text;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Queue_Array implements Queue {

    public static final int CAPACITY = 1000;//数组的默认容量
    protected int capacity;//数组的实际容量
    protected Object[] Q;//对象数组
    protected int f = 0;//队首元素的位置
    protected int r = 0;//队尾元素的位置

    //构造方法(空队列)
    public Queue_Array() {
        this(CAPACITY);
    }

    //按指定容量创建对象
    public Queue_Array(int cap) {
        capacity = cap;
        Q = new Object[capacity];
    }

    @Override
    public int size() {
        return (capacity - f + r) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public Object[] toArray(@NonNull Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
