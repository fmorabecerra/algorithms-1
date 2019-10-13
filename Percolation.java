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
        this.unionFind = new WeightedQuickUnionUF(n + 2); // Plus top and bottom virtual
        this.topVirtualIdx = n;
        this.bottomVirtalInx = n + 1;
        // Connect top virtual node to top row and also with the bottom
        for (int i = 1; i <= this.gridN; i++) {
            this.unionFind.union(topVirtualIdx, this.gridToUnionFindIndex(1, i));
            this.unionFind.union(bottomVirtalInx, this.gridToUnionFindIndex(this.gridN, i));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowAndCol(row, col);
        if (this.percolationGrid[row - 1][col - 1] == 0) {
            this.percolationGrid[row - 1][col - 1] = 1; // Maybe make a data structure for index.
            this.numOfOpenSites++;

            // Need to make connections if neighboring members are open.
            // connect w/ open neighbors func()
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
        Percolation per = new Percolation(3);
        per.open(3, 2);
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
}
