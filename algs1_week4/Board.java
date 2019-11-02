/* *****************************************************************************
 *  Name: Pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {
    private int n;
    private int[][] currentBoard;
    private int[][] goalBoard = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }, };

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;  // This may not be correct. Might be total number of elements.
        // this.currentBoard = Arrays.copyOf(tiles, this.n);
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

    // score = abs(row - row_true) + abs(col - col_true)
    // score = abs(2 - 2) + abs(0 - 1) = 0 + 1  (for 8)

    // row_true = (val / n) //Module
    //          = (8 / n) = 2

    // col_true = remainder(val /n) - 1
    //          = (8 % n) - 1 = 2 - 1 = 1

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

        Board that = (Board) y;
        if (this.n != that.n) return false;
        if (!Arrays.deepEquals(this.currentBoard, that.currentBoard)) return false;
        return true;
    }

    // // all neighboring boards
    // public Iterable<Board> neighbors() {
    //     // I forgot how to implement iterable. Comment for now.
    // }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return new Board(new int[3][3]);
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
    }

}
