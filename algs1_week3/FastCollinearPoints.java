/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segs;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validatePoints(points);

        for (Point p : points) {
            // Sort by natural order. Uses comparables
            Arrays.sort(points);
            // Mergesort sub array
            Arrays.sort(points, p.slopeOrder());

            // traverse sub array to find segments.
        }


        // Low let's look at all base points
    }

    // the number of line segments
    public int numberOfSegments() {
        return 0;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(this.segs, 3);
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
        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();
        //
        // // print and draw the line segments
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
    }

    // My private stuff down here
    private void validatePoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Constructor arg is null");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Element " + i + "is null");
        }
        // Check for duplicates
    }
}
