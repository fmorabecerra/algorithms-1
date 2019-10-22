/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int n = 4; // Default value
        if (args.length == 1) n = Integer.parseInt(args[0]);

        // Initialize Random Queue
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
        // Populate Queue
        while (!StdIn.isEmpty()) randQueue.enqueue(StdIn.readString());
        // Dequeue k items
        for (int i = 0; i < n; i++) {
            StdOut.println(randQueue.dequeue());
        }
    }
}
