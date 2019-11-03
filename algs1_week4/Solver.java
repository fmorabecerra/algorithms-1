/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private MinPQ<SearchNode> priorityQueue;
    private final int minMoves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("null");

        this.priorityQueue = new MinPQ<SearchNode>();
        this.priorityQueue.insert(new SearchNode(initial));
        this.minMoves = 0;

        StdOut.println("Min board: " + this.priorityQueue.min().board);
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board
    public int moves() {
        return this.minMoves;
    }

    // // sequence of boards in a shortest solution
    // public Iterable<Board> solution() {
    //     return Iterable; // Forgot how to do this for now. Will do later
    // }

    // // my test client. Comment/Uncomment as nessesary.
    // public static void main(String[] args) {
    //
    // }

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

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            // for (Board board : solver.solution())
            //     StdOut.println(board);
        }
    }


    // My stuff

    private class SearchNode {
        private Board board;

        public SearchNode(Board b) {
            this.board = b;
        }

        public int compareTo(SearchNode that) {
            return Integer.compare(this.board.manhattan(), that.board.manhattan());
        }
    }
}
