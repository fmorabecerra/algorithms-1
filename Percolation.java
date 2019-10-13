/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Corsera Assignment 1 Percolation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridN;
    private int[][] percolationGrid; // Set element to 1 if open. Zero if closed.
    private int numOfOpenSites;
    private WeightedQuickUnionUF unionFind;
    private int topVirtualIdx;
    private int bottomVirtalInx;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.gridN = n;
        this.percolationGrid = new int[n][n]; // By default all elements are initialized to zero
        this.numOfOpenSites = 0;

        // Initialize Union find data structure
        this.unionFind = new WeightedQuickUnionUF((n * n) + 2); // Plus top and bottom virtual
        this.topVirtualIdx = this.unionFind.count() - 2;
        this.bottomVirtalInx = this.unionFind.count() - 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowAndCol(row, col);
        if (this.percolationGrid[row - 1][col - 1] == 0) {
            this.percolationGrid[row - 1][col - 1] = 1; // Maybe make a data structure for index.
            this.numOfOpenSites++;

            // Need to make connections if neighboring members are open.
            // connect w/ open neighbors func()
            this.connectWithOpenNeighbors(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowAndCol(row, col);
        // Return whether element in grid is open (equals 1)
        return (this.percolationGrid[row - 1][col - 1] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRowAndCol(row, col);
        // Check if location is connected with virtual object at the top.
        return this.unionFind.connected(this.topVirtualIdx, this.gridToUnionFindIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Check if top and bottom virtual nodes are connected.
        return this.unionFind.connected(this.topVirtualIdx, this.bottomVirtalInx);
    }

    // test client (optional)
    public static void main(String[] args) {
        StdRandom.gaussian();
        // WeightedQuickUnionUF WQUUF = new WeightedQuickUnionUF(4);
        StdOut.println("Hello, World!");
        // StdOut.println("Is 1 and 3 connected: %d", WQUUF.connected(1, 3));
        Percolation per = new Percolation(5);
        per.open(2, 2);
        per.open(2, 3);
    }

    private void checkRowAndCol(int row, int column) {
        if (row < 1 || row > gridN) {
            throw new IllegalArgumentException(Integer.toString(row));
        }
        if (column < 1 || column > gridN) {
            throw new IllegalArgumentException(Integer.toString(column));
        }
    }

    private int gridToUnionFindIndex(int row, int column) {
        return (this.gridN * (row - 1) + (column - 1));
    }

    private void connectWithOpenNeighbors(int row, int column) {
        int leftColumn = column - 1;
        int rightColumn = column + 1;
        int topRow = row - 1;
        int bottomRow = row + 1;
        // Check to make sure that you are not on left edge
        if (column != 1 && this.isOpen(row, leftColumn)) {
            this.unionFind.union(this.gridToUnionFindIndex(row, column),
                                 this.gridToUnionFindIndex(row, leftColumn));
        }
        // Check to make sure that you are not on right edge
        if (column != this.gridN && this.isOpen(row, rightColumn)) {
            this.unionFind.union(this.gridToUnionFindIndex(row, column),
                                 this.gridToUnionFindIndex(row, rightColumn));
        }
        // Connect with top virtual node if in top row.
        if (row == 1) {
            this.unionFind.union(this.topVirtualIdx, this.gridToUnionFindIndex(row, column));
        }
        else {
            if (this.isOpen(topRow, column)) {
                this.unionFind.union(this.gridToUnionFindIndex(row, column),
                                     this.gridToUnionFindIndex(topRow, column));
            }
        }
        // Connect with bottom virtual node if on bottom row.
        if (row == this.gridN) {
            this.unionFind.union(this.bottomVirtalInx, this.gridToUnionFindIndex(row, column));
        }
        else {
            if (this.isOpen(bottomRow, column)) {
                this.unionFind.union(this.gridToUnionFindIndex(row, column),
                                     this.gridToUnionFindIndex(bottomRow, column));
            }
        }
    }
}
