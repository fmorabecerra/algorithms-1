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
    }


    // My private Methods:

    // The slopeOrder() method should return a comparator that compares its
    // two argument points by the slopes they make with the invoking point (x0, y0).
    // Formally, the point (x1, y1) is less than the point (x2, y2) if and only if
    // the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
    // Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
    private class PolarOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double dy1 = p1.y - Point.this.y;
            double dy2 = p2.y - Point.this.y;
            if (dy1 == 0 && dy2 == 0) return +0;  // p, q1, q2 horizontal
            else if (dy1 >= 0 && dy2 < 0) return -1; // p1 above p; p2 below p
            else if (dy2 >= 0 && dy1 < 0) return +1;  // p1 below p; p2 above p
            else return -ccw(Point.this, p1, p2);  // both above or below p
        }
    }

    // Slide 77 from week 2 part 2.
    // Given three points a, b, and c, is a → b→ c a counterclockwise turn?
    //  ・Determinant (or cross product) gives 2x signed area of planar triangle.
    private static int ccw(Point a, Point b, Point c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0) return -1; // clockwise
        else if (area2 > 0) return +1; // counter-clockwise
        else return 0; // collinear
    }
}
