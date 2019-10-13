/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int percolationTrials;
    private double[] percolationThreshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.validateTrialsAndN(n, trials);
        this.percolationTrials = trials;
        this.percolationThreshold = new double[trials];

        // Start the test loop.
        for (int trial = 0; trial < trials; trial++) {
            percolationThreshold[trial] = 0.0;
        }
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
        return (this.mean() - ((1.96 * this.stddev()) / Math.sqrt(this.percolationTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean() + ((1.96 * this.stddev()) / Math.sqrt(this.percolationTrials)));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length == 2) {
            PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]),
                                                              Integer.parseInt(args[1]));
            // Print mean
            StdOut.println(percStats.mean());
            // Print stdDev
            // Print confidence interval.
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
}
