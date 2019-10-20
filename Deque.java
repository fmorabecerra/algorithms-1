/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Week 2 assignment for Algorithms I, Coursera
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
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

        if (this.last == null)
            this.last = this.first;
        else
            oldfirst.previous = this.first;
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
        if (isEmpty()) this.last = this.first;
        else this.first.previous = null;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Node oldLast = this.last;
        this.last = oldLast.previous;
        if (this.last == null) this.first = this.last;
        else this.last.next = null;
        this.totalNodes--;
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
        deque.addLast("Hello");
        StdOut.println("Adding: Hola");
        deque.addLast("Hola");
        StdOut.println("Adding: Bonjour");
        deque.addLast("Bonjour");
        StdOut.println("Size of deque: " + deque.size());

        for (String s : deque)
            StdOut.println(s);

        StdOut.println("Item removed from stack: " + deque.removeLast());
        StdOut.println("Item removed from stack: " + deque.removeLast());
        StdOut.println("Item removed from stack: " + deque.removeLast());
        StdOut.println("Size of deque: " + deque.size());
        if (!deque.isEmpty())
            StdOut.println("Item removed from stack: " + deque.removeLast());
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

        public void remove() {
            throw new UnsupportedOperationException("Remove func not supported.");
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException("End of list. No more Items.");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
