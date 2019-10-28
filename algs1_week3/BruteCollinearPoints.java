/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int totalSegments;
    private final LineSegment[] validLineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        ArrayList<LineSegment> lineSegmentsArrayList = new ArrayList<LineSegment>(0);
        Point[] fourPoints = new Point[4];

        // Need to throw exception here if point are equal
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        // To check whether the 4 points p, q, r, and s are collinear, check whether
                        // the three slopes between p and q, between p and r, and between p and s are all equal.
                        double slopePtoQ = points[p].slopeTo(points[q]);
                        double slopePtoR = points[p].slopeTo(points[r]);
                        double slopePtoS = points[p].slopeTo(points[s]);
                        // Check if we have multiple entries
                        if (slopePtoQ == Double.NEGATIVE_INFINITY
                                || slopePtoR == Double.NEGATIVE_INFINITY
                                || slopePtoS == Double.NEGATIVE_INFINITY)
                            throw new IllegalArgumentException("Identical points found");
                        // Check if points are collinear
                        if (Double.compare(slopePtoQ, slopePtoR) == 0
                                && Double.compare(slopePtoQ, slopePtoS) == 0) {
                            this.totalSegments++;
                            // Now we have to store the line segments
                            fourPoints[0] = points[p];
                            fourPoints[1] = points[q];
                            fourPoints[2] = points[r];
                            fourPoints[3] = points[s];
                            Arrays.sort(fourPoints);
                            LineSegment seg = new LineSegment(fourPoints[0], fourPoints[3]);
                            lineSegmentsArrayList.add(seg);
                        }
                    }
                }
            }
        }

        this.validLineSegments = new LineSegment[this.totalSegments];
        // Convert the line segments Array list to array.
        for (int i = 0; i < this.totalSegments; i++) {
            this.validLineSegments[i] = lineSegmentsArrayList.get(i);
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return this.totalSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(this.validLineSegments, this.totalSegments);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


    // My private stuff down here
    private void validatePoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Constructor arg is null");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Element " + i + "is null");
        }
    }
}

