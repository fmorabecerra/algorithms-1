/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    private final int minMoves;
    private final boolean solvable;
    private final ArrayList<Board> solutionPath;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("null");

        MinPQ<SearchNode> priorityQueue = new MinPQ<SearchNode>();
        // Insert initial board to queue.
        priorityQueue.insert(new SearchNode(initial, null, 0, false));
        // Insert twin to see which one gives a solution first
        priorityQueue.insert(new SearchNode(initial.twin(), null, 0, true));
        // Initialize solution path iterable array list
        this.solutionPath = new ArrayList<Board>();

        SearchNode currentNode;
        while (true) {
            // Pop element from queue with lowest priority score.
            currentNode = priorityQueue.delMin();
            // Check if Goal board is found. End search if so.
            if (currentNode.board.isGoal()) {
                this.minMoves = currentNode.moves;
                this.solvable = !currentNode.isTwin;
                break;
            }
            // Add all neighbors to queue for next round.
            for (Board neighbor : currentNode.board.neighbors()) {
                if (currentNode.previousNode == null
                        || !neighbor.equals(currentNode.previousNode.board))
                    priorityQueue.insert(new SearchNode(neighbor, currentNode,
                                                        currentNode.moves + 1, currentNode.isTwin));
            }
        }

        // Build array list here
        while (currentNode != null) {
            solutionPath.add(currentNode.board);
            currentNode = currentNode.previousNode;
        }
        // Reverse the list
        Collections.reverse(solutionPath);
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return this.minMoves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return this.solutionPath; // Forgot how to do this for now. Will do later
    }

    // test client.
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        StdOut.println("initial board: \n" + initial);
        StdOut.println("Twin board \n" + initial.twin());

        // solve the puzzle
        Solver solver = new Solver(initial);

        StdOut.println("size of arraylist:" + solver.solutionPath.size());

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }


    // We need a search node class for priority queue to work.
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode previousNode;
        private final int manhattanScore;
        private final int moves;
        private final boolean isTwin;

        public SearchNode(Board b, SearchNode previous, int mvs, boolean isTwin) {
            this.board = b;
            this.previousNode = previous;
            this.manhattanScore = this.board.manhattan();
            this.moves = mvs;
            this.isTwin = isTwin;
        }

        public int compareTo(SearchNode that) {
            return Integer
                    .compare(this.manhattanScore + this.moves, that.manhattanScore + that.moves);
        }
    }
}
