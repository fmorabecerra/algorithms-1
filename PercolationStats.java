/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int percolationGridN;
    private final int percolationTrials;
    private double[] percolationThreshold;
    private final double confidence95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.validateTrialsAndN(n, trials);
        this.percolationGridN = n;
        this.percolationTrials = trials;
        this.percolationThreshold = new double[trials];

        // Do the simulations.
        this.monteCarloSimulation();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.percolationThreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percolationThreshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (this.mean() - ((this.confidence95 * this.stddev()) / Math
                .sqrt(this.percolationTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean() + ((this.confidence95 * this.stddev()) / Math
                .sqrt(this.percolationTrials)));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length == 2) {
            PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]),
                                                              Integer.parseInt(args[1]));
            // Print mean
            StdOut.println("mean                    = " + percStats.mean());
            // Print stdDev
            StdOut.println("stddev                  = " + percStats.stddev());
            // Print confidence interval.
            StdOut.println(
                    "95% confidence interval = [" + percStats.confidenceLo() + " " + percStats
                            .confidenceHi() + "]");
        }
    }

    private void validateTrialsAndN(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value " + n + "for n is not <= 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("Value " + trials + "for trials is not <= 0.");
        }
    }

    private void monteCarloSimulation() {
        // Start each trial
        for (int trial = 0; trial < this.percolationTrials; trial++) {
            Percolation perc = new Percolation(this.percolationGridN);
            while (!perc.percolates()) {
                // Generate random site to open.
                int row = StdRandom.uniform(this.percolationGridN) + 1;
                int col = StdRandom.uniform(this.percolationGridN) + 1;

                perc.open(row, col);
            }
            this.percolationThreshold[trial] = perc.numberOfOpenSites() / Math
                    .pow(this.percolationGridN, 2);
        }
    }
}
