/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Corsera Assignment 1 Percolation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private int gridN;
    private int[][] percolationGrid; // Set element to 1 if open

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.gridN = n;
        this.percolationGrid = new int[n][n]; // By default all elements are initialized to zero
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowAndCol(row, col);
        this.percolationGrid[row - 1][col - 1] = 1; // You need to address this indexing issue.
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowAndCol(row, col);
        // Return whether element in grid is open (equals 1)
        return (this.percolationGrid[row][col] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRowAndCol(row, col);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {
        StdRandom.gaussian();
        // WeightedQuickUnionUF WQUUF = new WeightedQuickUnionUF(4);
        StdOut.println("Hello, World!");
        // StdOut.println("Is 1 and 3 connected: %d", WQUUF.connected(1, 3));
        Percolation per = new Percolation(3);
        per.open(3, 2);
    }

    private void checkRowAndCol(int row, int column) {
        if (row < 0 || row > gridN) {
            throw new IllegalArgumentException(Integer.toString(row));
        }
        if (column < 0 || column > gridN) {
            throw new IllegalArgumentException(Integer.toString(column));
        }
    }
}
