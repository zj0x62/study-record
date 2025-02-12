package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 15:49
 * @Description:
 */
public interface Collection<E, L> extends Iterable<E> {

    boolean add(E e);

    boolean remove(E e);

    boolean addLink(String key, L l);

    boolean removeLink(String key);

    Iterator<E> iterator();
}
