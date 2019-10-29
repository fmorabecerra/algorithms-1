/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board
    public int moves() {
        return 0;
    }

    // // sequence of boards in a shortest solution
    // public Iterable<Board> solution() {
    //     return Iterable; // Forgot how to do this for now. Will do later
    // }

    // my test client. Comment/Uncomment as nessesary.
    public static void main(String[] args) {

    }

    // // test client.
    // public static void main(String[] args) {
    //
    //     // create initial board from file
    //     In in = new In(args[0]);
    //     int n = in.readInt();
    //     int[][] tiles = new int[n][n];
    //     for (int i = 0; i < n; i++)
    //         for (int j = 0; j < n; j++)
    //             tiles[i][j] = in.readInt();
    //     Board initial = new Board(tiles);
    //
    //     // solve the puzzle
    //     Solver solver = new Solver(initial);
    //
    //     // print solution to standard output
    //     if (!solver.isSolvable())
    //         StdOut.println("No solution possible");
    //     else {
    //         StdOut.println("Minimum number of moves = " + solver.moves());
    //         for (Board board : solver.solution())
    //             StdOut.println(board);
    //     }
    // }

}
