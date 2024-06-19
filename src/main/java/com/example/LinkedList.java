package com.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * Class implementation of Node<T>
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Constructor of Node
         * @param data
         */
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Adds a new node at the end of the list
     * @param data
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Removes a node by value
     * @param data
     * @return true if the node was removed, false otherwise
     */
    public boolean remove(T data) {
        if (head == null) return false;

        Node<T> current = head;
        while (current != null && !current.data.equals(data)) {
            current = current.next;
        }

        if (current == null) return false;

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }

        return true;
    }

    /**
     * Gets a node by index
     * @param index
     * @return data at the specified index
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) {
        Node<T> current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            count++;
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
    }

    /**
     * Prints the entire list
     */
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            if(current.next != null){
                System.out.print(current.data + ", ");
                current = current.next;
            }else{
                System.out.print(current.data);
                current = current.next;
            }
        }
        System.out.println();
    }

    /**
     * Gets the size of the list
     * @return the size of the list
     */
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Gets the next node of the specified node
     * @param data the data of the node to find the next node
     * @return the data of the next node, or null if there is no next node
     */
    public T nextNode(T data) {
        Node<T> current = head;
        while (current != null && !current.data.equals(data)) {
            current = current.next;
        }
        return (current != null && current.next != null) ? current.next.data : null;
    }

    /**
     * Gets the prior node of the specified node
     * @param data the data of the node to find the prior node
     * @return the data of the prior node, or null if there is no prior node
     */
    public T priorNode(T data) {
        Node<T> current = head;
        while (current != null && !current.data.equals(data)) {
            current = current.next;
        }
        return (current != null && current.prev != null) ? current.prev.data : null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        LinkedListIterator(Node<T> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
