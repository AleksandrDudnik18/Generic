package com.company;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class LinkedList<E> implements List<E> {

    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public E getElement() {
            return element;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public void add(E element) {
        final Node<E> node = new Node(element);
        if (first == null) {
            first = last = node;

        } else {
            last.setNext(node);
            node.setPrev(last);
            last = node;
        }
        size++;
    }

    //TODO: идти с конца если индекс больше чем сайз \ 2
    private Node<E> getNode(int index) {
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("size = " + size + " index = " + index);
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        checkElementIndex(index);

        if (size == 0) {
            add(element);
        } else {
            Node<E> node = new Node(element);

            if (index == 0) {
                first.setPrev(node);
                node.setNext(first);
                first = node;
            } else if (index == size) {
                last.setNext(node);
                node.setPrev(last);
                last = node;
            } else {
                Node<E> right = getNode(index);
                Node<E> left = right.getPrev();
                node.setPrev(left);
                node.setNext(right);

                left.setNext(node);
                right.setPrev(node);

            }
            size++;
        }
    }

    @Override
    public void addAll(Collection<? extends E> elements) {
        for (E element : elements) {
            add(element);
        }
    }

    public static <E> LinkedList<E> copy(Collection<? extends E> elements) {
        LinkedList<E> linkedList = new LinkedList<>();
        linkedList.addAll(elements);

        return linkedList;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).getElement();
    }

    @Override
    public E remove(int index) {
        final Node<E> removed;

        checkElementIndex(index);

        if (size == 1) {
            removed = first;
            first = last = null;
        } else {
            if (index == 0) {
                removed = first;
                first = first.getNext();
                first.prev = null;
            } else if (index == size - 1) {
                removed = last;
                last = last.getPrev();
                last.next = null;
            } else {
                removed = getNode(index);
                Node<E> prev = removed.getPrev();
                Node<E> next = removed.getNext();

                prev.setNext(next);
                next.setPrev(prev);
            }
        }
        size--;
        return removed.getElement();
    }

    class LinkedListIterator implements Iterator<E> {

        private Node<E> currentNode;

        private LinkedListIterator(Node<E> first) {
            currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E next = currentNode.element;
            currentNode = currentNode.next;
            return next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(first);
    }
}
