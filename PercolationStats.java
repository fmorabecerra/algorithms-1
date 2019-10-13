/* *****************************************************************************
 *  Name: Francisco
 *  Date:
 *  Description: Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] percolationThreshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.validateTrialsAndN(n, trials);
        this.percolationThreshold = new double[trials];

        // Start the test loop.
        for (int trial = 0; trial < trials; trial++) {
            percolationThreshold[i] = 0.0;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
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
