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
    private final double confidence95;
    private final double trialsSqrt;

    // Stash stats. Only compute them once
    private double mcMean;
    private double mcStdDev;
    private double mcLoCon;
    private double mcHiCon;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.validateTrialsAndN(n, trials);
        this.percolationGridN = n;
        this.percolationTrials = trials;
        this.percolationThreshold = new double[trials];

        // Nessesary constants
        this.confidence95 = 1.96;
        this.trialsSqrt = Math.sqrt(trials);

        // Do the simulations.
        this.monteCarloSimulation();
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mcMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.mcStdDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mcLoCon;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mcHiCon;
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
            throw new IllegalArgumentException("Value " + n + " for n is <= 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("Value " + trials + " for trials is <= 0.");
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
        // Calculate stats one time here
        this.mcMean = StdStats.mean(this.percolationThreshold);
        this.mcStdDev = StdStats.stddev(this.percolationThreshold);
        this.mcLoCon = (this.mcMean - ((this.confidence95 * this.mcStdDev) / this.trialsSqrt));
        this.mcHiCon = (this.mcMean + ((this.confidence95 * this.mcStdDev) / this.trialsSqrt));
    }
}
