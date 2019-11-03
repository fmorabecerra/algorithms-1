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

        // class ManhattanOrder implements Comparator<Board> {
        //     // @Override
        //     public int compare(Board b1, Board b2) {
        //         // return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
        //         return 0;
        //     }
        // }

        // Comparator<Board> manhattanComparator = new Comparator<Board>() {
        // {
        //     @Override
        //     public int compare(Board b1, Board b2) {
        //         // return e1.getName().compareTo(e2.getName());
        //         return 0;
        //     }
        // };

        this.priorityQueue = new MinPQ<SearchNode>();
        this.priorityQueue.insert(new SearchNode(initial));
        // this.priorityQueue(initial.getClass().getMethod("manhattan", null));
        // this.priorityQueue.insert(initial);
        this.minMoves = 0;
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
    // private interface Comparator<Board> {
    //     int compare(Board b1, Board b2);
    // }
    //
    // private int compare(Board b1, Board b2) {
    //     if (b1.equals(b2)) return 0;
    //     return 0;
    // }
    // private Comparator<Board> manhattanOrder () {
    //     /* YOUR CODE HERE */
    //     return (new ManhattanOrder());
    // }

    // private class ManhattanOrder implements Comparator<Board> {
    //     @Override
    //     public int compare(Board b1, Board b2) {
    //         // return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
    //         return 0;
    //     }
    // }

    private class SearchNode { //implements Comparable<SearchNode> {
        private Board board;

        public SearchNode(Board b) {
            this.board = b;
        }

        public int compareTo(SearchNode that) {
            return Integer.compare(this.board.manhattan(), that.board.manhattan());
        }
        // public Comparator<SearchNode> slopeOrder() {
        //     /* YOUR CODE HERE */
        //     return (new manhatOrder());
        // }

        // private class manhatOrder implements Comparator<SearchNode> {
        //     public int compare(SearchNode that) {
        //         // return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
        //         return Integer.compare(SearchNode.this.board.manhattan(), that.board.manhattan());
        //     }
        // }
    }
}
