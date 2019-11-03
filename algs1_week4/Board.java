/* *****************************************************************************
 *  Name: Pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int n;
    private int[][] currentBoard;
    private int[][] goalBoard = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }, };

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.currentBoard = tiles.clone();
    }

    // string representation of this board
    public String toString() {
        String text = Arrays.deepToString(this.currentBoard);
        // Remove all the stuff that you don't want
        text = text.replace(", [", "\n").replace(",", "").replace("]", "").replace("[", "");
        // Put everything together
        return Integer.toString(this.n) + "\n" + text;
    }

    // board dimension n
    public int dimension() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        int hammingScore = 0;
        // Iterate over board and compute the hamming distance
        for (int row = 0; row < this.currentBoard.length; row++) {
            for (int col = 0; col < this.currentBoard[row].length; col++) {
                if (this.currentBoard[row][col] != this.goalBoard[row][col]
                        && this.currentBoard[row][col] != 0)
                    hammingScore++;
            }
        }
        return hammingScore;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanScore = 0;
        // Iterate over board and compute the Manhattan distance
        for (int row = 0; row < this.currentBoard.length; row++) {
            for (int col = 0; col < this.currentBoard[row].length; col++) {
                if (this.currentBoard[row][col] != this.goalBoard[row][col]
                        && this.currentBoard[row][col] != 0)
                    manhattanScore += Math.abs(row - ((this.currentBoard[row][col] - 1) / this.n))
                            + Math.abs(col - (this.currentBoard[row][col] - 1) % this.n);
            }
        }
        return manhattanScore;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return Arrays.deepEquals(this.goalBoard, this.currentBoard);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        // Cast y to board object
        Board that = (Board) y;
        if (this.n != that.n) return false;
        if (!Arrays.deepEquals(this.currentBoard, that.currentBoard)) return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        // Fill in iterable array list
        for (int j = 0; j < 3; j++) {
            neighbors.add(new Board(new int[3][3]));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinBoard = this.currentBoard.clone();
        int temp = twinBoard[2][0];
        twinBoard[2][0] = twinBoard[2][1];
        twinBoard[2][1] = temp;
        return new Board(twinBoard);
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        StdOut.println(initial.toString());
        StdOut.println("Is goal?: " + initial.isGoal());
        StdOut.println("Hamming score: " + initial.hamming());
        StdOut.println("Manhattan score: " + initial.manhattan());
        StdOut.println("Twin Board: \n" + initial.twin());

        // for (Board b : initial.neighbors()) {
        //     StdOut.println(b.toString());
        // }
    }


    // // My Stuff
}
