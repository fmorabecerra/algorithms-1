/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Week 2 assignment for Algorithms I, Coursera
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (item == null) throw new IllegalArgumentException("null");
        Node oldfirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldfirst;
        this.first.previous = null;
        this.totalNodes++; // Increment

        if (this.last == null) {
            this.last = this.first;
        }
        else {
            oldfirst.previous = this.first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null");
        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        this.totalNodes++;

        if (isEmpty()) {
            this.first = this.last;
            this.last.previous = null;
        }
        else {
            oldLast.next = this.last;
            this.last.previous = oldLast;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() { // Pop
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Node oldFirst = this.first;
        this.first = this.first.next;
        this.totalNodes--;
        if (isEmpty()) {
            this.last = this.first;
            return oldFirst.item;
        }
        else {
            this.first.previous = null;
            return oldFirst.item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
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
        StdOut.println("Hello World");

        // Start Deque
        Deque<String> deque = new Deque<String>();
        StdOut.println("Adding: Hello");
        deque.addFirst("Hello");
        StdOut.println("Adding: Hola");
        deque.addFirst("Hola");
        // deque.addFirst("Bonjour");
        StdOut.println("Item removed from stack: " + deque.removeFirst());
        StdOut.println("Item removed from stack: " + deque.removeFirst());
        StdOut.println("Item removed from stack: " + deque.removeFirst());
    }

    // Private API stuff

    private class Node {
        Item item;
        Node next;
        Node previous;
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
