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
    private Item[] queueArray;
    private int startN;
    private int endN;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queueArray = (Item[]) new Object[3];
        this.startN = 0;
        this.endN = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (this.endN == this.startN);
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.endN - this.startN;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot enqueue null");
        // First we need to shift to left and then double in size if needed.
        if (this.endN == this.queueArray.length) resize(2 * this.queueArray.length);
        this.queueArray[endN++] = item;
    }

    // remove and return a random item
    public Item dequeue() { // like deque will return item at begin
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        Item oldFirst = this.queueArray[this.startN];
        this.queueArray[this.startN] = null;  // Remove in future. Unessesary step right now.
        this.startN++;
        // If start catches up to end then reset both to zero index.
        if (this.startN == this.endN) {
            this.startN = 0;
            this.endN = 0;
        }
        return oldFirst;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        return this.queueArray[StdRandom.uniform(this.startN, this.endN)];
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
        // Return random samples
        for (int i = 0; i < 20; i++) {
            StdOut.println("Random Item: " + randQueue.sample());
        }
        // Start dequeing
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

    private void shiftAllToTheStart() {
        for (int i = this.startN; i < this.endN; i++) {
            this.queueArray[i - this.startN] = this.queueArray[i];
        }
        this.endN -= this.startN;
        this.startN = 0;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = this.startN; i < this.endN; i++)
            copy[i] = this.queueArray[i];
        this.queueArray = copy;
    }

}
