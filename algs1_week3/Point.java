/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally, if the two points are
     * (x0, y0) and (x1, y1), then the slope is (y1 - y0) / (x1 - x0). For completeness, the slope
     * is defined to be +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and Double.NEGATIVE_INFINITY if
     * (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that.x == this.x && that.y == this.y)  // Points are Identical
            return Double.NEGATIVE_INFINITY;
        if (that.x - this.x == 0)  // Line is vertical
            return Double.POSITIVE_INFINITY;
        if (that.y == this.y)  // Line is horizontal
            return +0.0;

        return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally, the invoking
     * point (x0, y0) is less than the argument point (x1, y1) if and only if either y0 < y1 or if
     * y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point (x0 = x1 and y0 =
     * y1); a negative integer if this point is less than the argument point; and a positive integer
     * if this point is greater than the argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y > that.y || (this.y == that.y && this.x > that.x)) {
            return 1; // if this point is More than THAT
        }
        else if (this.y < that.y || this.x < that.x) {
            return -1; // if this point is less than THAT
        }
        else {
            return 0;  // points are equal.
        }
    }

    /**
     * Compares two points by the slope they make with this point. The slope is defined as in the
     * slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return (new PolarOrder());
    }


    /**
     * Returns a string representation of this point. This method is provide for debugging; your
     * program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        StdOut.println("Hello, World!");
        // Point p1 = new Point(1, 1);
        // Point p2 = new Point(2, 1);
        // p1.slopeTo(p2);

        //   * sign of compare(), where p, q, and r have coordinates in [0, 10)
        // Failed on trial 1 of 100000
        // p                         = (5, 9)
        // q                         = (8, 6)
        // r                         = (1, 3)
        // student   p.compare(q, r) = 1
        // reference p.compare(q, r) = -1
        // reference p.slopeTo(q)    = -1.0
        // reference p.slopeTo(r)    = 1.5

        // Point p = new Point(5, 9);
        // Point q = new Point(8, 6);
        // Point r = new Point(1, 3);

        Point p = new Point(10354, 20973);
        Point q = new Point(30825, 7371);
        Point r = new Point(26210, 32375);
        StdOut.println("My anwer: " + p.slopeOrder().compare(q, r));

        // Point p0 = new Point(1, 0);
        // Point p1 = new Point(1, 1);
        // Point p2 = new Point(2, 2);
        // Point p3 = new Point(3, 3);
        // StdOut.println("Are slopes equal: " + p0.slopeOrder().compare(p3, p2));
        StdOut.println("Result of double compare: " + Double
                .compare(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));


    }


    // My private Methods:

    // The slopeOrder() method should return a comparator that compares its
    // two argument points by the slopes they make with the invoking point (x0, y0).
    // Formally, the point (x1, y1) is less than the point (x2, y2) if and only if
    // the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
    // Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
    private class PolarOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
        }
    }
}
