package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void testAddAndGet() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.add("World");
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.get(1);
    }

    @Test
    public void testSize() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals(0, list.size());
        list.add("Hello");
        assertEquals(1, list.size());
        list.add("World");
        assertEquals(2, list.size());
    }

    @Test
    public void testPrintList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.add("World");
        list.printList(); // This should print "Hello, World"
    }

    @Test
    public void testForEach() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.add("World");

        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append(" ");
        }

        assertEquals("Hello World ", result.toString());
    }
}

