package com.company;

import java.util.Collection;
import java.util.Iterator;

public interface List<E> extends Iterable<E> {
    void add(E element);

    int getSize();

    void add(int index, E element);

    void addAll(Collection<? extends E> elements);


    E get(int index);
    E remove(int index);
}
