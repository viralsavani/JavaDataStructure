package com.DataStructure.List;

import java.util.Arrays;

/**
 * Created by VIRAL on 4/2/2015.
 * Reference taken from http://www.vogella.com/tutorials/JavaDatastructures/article.html
 */
public class MyList<E> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 11;
    private Object elements[];

    public MyList(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyList(int initialSize){
        elements = new Object[initialSize];
    }

    public void add(E e){
        if(size == elements.length){
            ensureCapacity();
        }
        elements[size++] = e;
    }

    private void ensureCapacity(){
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements,newSize);
    }

    public E get(int i){
        if(i >= size || i < 0){
            throw new IndexOutOfBoundsException("Index:: "+i+" out of range");
        }
        return (E) elements[i];
    }

    public int size(){
        return (size-1);
    }

    public void remove(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index:: "+index+" out of range");
        }
        elements[index] = null;
        condenseList(index);
        size--;
    }

    private void condenseList(int start){
        for (int i = start; i < size; i++) {
            elements[i] = elements[i+1];
        }
    }

}
