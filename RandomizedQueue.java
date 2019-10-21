/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

// public class RandomizedQueue<Item> implements Iterable<Item> {
public class RandomizedQueue<Item> {
    private Item[] stackArray;
    private int arrayN;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.stackArray = (Item[]) new Object[3];
        this.arrayN = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (this.arrayN == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.arrayN;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot enqueue null");
        // First we need to shift to left and then double in size if needed.
        if (this.arrayN == this.stackArray.length) resize(2 * this.stackArray.length);
        this.stackArray[arrayN++] = item;
    }

    // remove and return a random item
    public Item dequeue() { // like deque will return item at begin
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        this.arrayN--;
        Item oldLast = this.stackArray[this.arrayN];
        this.stackArray[this.arrayN] = null;  // Not needed in future. Can ignore.
        return oldLast;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        return this.stackArray[StdRandom.uniform(this.arrayN)];
    }

    // // return an independent iterator over items in random order
    // public Iterator<Item> iterator() {
    //     return new ReverseArrayIterator();
    // }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Hello world!");

        // Make your randomized queue
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
        StdOut.println("the size of my queue is: " + randQueue.size());
        randQueue.enqueue("First");
        randQueue.enqueue("Second");
        randQueue.enqueue("Third");
        randQueue.enqueue("Fourth");
        randQueue.enqueue("Fifth");
        // Return random samples
        // for (int i = 0; i < 20; i++) {
        //     StdOut.println("Random Item: " + randQueue.sample());
        // }
        // Start dequeing
        StdOut.println("Removing: " + randQueue.dequeue());
        StdOut.println("Removing: " + randQueue.dequeue());
        StdOut.println("Removing: " + randQueue.dequeue());
        StdOut.println("Removing: " + randQueue.dequeue());
        StdOut.println("Removing: " + randQueue.dequeue());
    }


    // !!!! All Private Methods Below !!!!

    // private class ReverseArrayIterator implements Iterator<Item> {
    //     private int i = totalItems();
    //
    //     public boolean hasNext() {
    //         return i > 0;
    //     }
    //
    //     public void remove() {  /* not supported */ }
    //
    //     public Item next() {
    //         // return s[--i];
    //     }
    // }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < this.arrayN; i++)
            copy[i] = this.stackArray[i];
        this.stackArray = copy;
    }
}
