/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private int minMoves;
    private final ArrayList<Board> solutionPath;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("null");

        MinPQ<SearchNode> priorityQueue = new MinPQ<SearchNode>();
        priorityQueue.insert(new SearchNode(initial));
        this.minMoves = 0;
        this.solutionPath = new ArrayList<Board>();

        while (true) {
            // pop element from queue
            SearchNode currentNode = priorityQueue.delMin();
            // Stash current search node in solution path
            solutionPath.add(currentNode.board);
            if (currentNode.board.isGoal()) break;
            this.minMoves++;  // Move to afterwards
            for (Board neighbor : currentNode.board.neighbors()) {
                if (solutionPath.size() == 1) {
                    priorityQueue.insert(new SearchNode(neighbor));
                }
                else {
                    // Do optimization
                    if (!neighbor.equals(solutionPath.get(solutionPath.size() - 2)))
                        priorityQueue.insert(new SearchNode(neighbor));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board
    public int moves() {
        return this.minMoves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return this.solutionPath; // Forgot how to do this for now. Will do later
    }

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

        StdOut.println("size of arraylist:" + solver.solutionPath.size());
        StdOut.println("board:" + solver.solutionPath.get(solver.solutionPath.size() - 2));

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }


    // My stuff

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int manhattanScore;

        public SearchNode(Board b) {
            this.board = b;
            this.manhattanScore = this.board.manhattan();
        }

        public int compareTo(SearchNode that) {
            return Integer.compare(this.manhattanScore, that.manhattanScore);
        }
    }
}
