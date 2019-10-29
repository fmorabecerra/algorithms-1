/* *****************************************************************************
 *  Name: Pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
 **************************************************************************** */

public class Board {
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;  // This may not be correct. Might be total number of elements.
    }

    // string representation of this board
    public String toString() {
        return "Hello";
    }

    // board dimension n
    public int dimension() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
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

    }

}
