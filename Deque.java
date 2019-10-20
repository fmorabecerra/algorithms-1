/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Week 2 assignment for Algorithms I, Coursera
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // public class Deque<Item> {
    private Node first;
    private Node last;
    private int totalNodes;

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.totalNodes = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.totalNodes;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldfirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldfirst;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;

        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() { // Pop
        Item item = this.first.item;
        this.first = this.first.next;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Node oldLast = this.last;
        // Have to come up with a method to set 2nd to last Node to last
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Lets try to find out size of this.
        Deque<String> deque = new Deque<String>();
        deque.addFirst("Hello");

        StdOut.println("Hello World");

        // Checkout the size
        // The size stuff is not helping because it does not include each stack node size.
        printObjectSize(deque);
        deque.addFirst("Hola");
        printObjectSize(deque);
    }

    // Private API stuff

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {  /* not supported */ }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
