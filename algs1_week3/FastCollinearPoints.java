/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private int totalSegments;
    private final LineSegment[] validLineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validatePoints(points);

        ArrayList<LineSegment> lineSegmentsArrayList = new ArrayList<LineSegment>(0);
        Point[] sortingArray = Arrays.copyOf(points, points.length);

        // Sort array by slope for each point
        for (int i = 0; i < points.length; i++) {

            // Mergesort sub array
            Arrays.sort(sortingArray, points[i].slopeOrder());

            // traverse sub array to find segments.
            int state = 0; // 0 = looking, 1 = found segment
            int maxPointIndex = 0;
            for (int j = 1; j < points.length; j++) {
                if (points[i].slopeOrder().compare(sortingArray[j - 1], sortingArray[j]) == 0) {
                    StdOut.println("Matching slopes found");
                    if (state == 0) {
                        state++; // Change state. Found segment
                        if (points[i].compareTo(sortingArray[j - 1]) > 0)
                            break;
                        maxPointIndex = j - 1;
                    }

                    // check that p is min. if not break
                    if (points[i].compareTo(sortingArray[j]) > 0) break;
                    StdOut.println("Good line segment found");
                    // stash the max point
                    if (sortingArray[j].compareTo(sortingArray[maxPointIndex]) > 0)
                        maxPointIndex = j;
                }
                else {  // Pattern is not found
                    if (state == 1) {
                        // Here is where you stash the segment
                        lineSegmentsArrayList
                                .add(new LineSegment(points[i], sortingArray[maxPointIndex]));
                        break; // Leave because no more are found.
                    }
                }
            }
        }
        // Convert array list to segment array
        StdOut.println("Does it ever get here?");
        this.totalSegments = lineSegmentsArrayList.size();
        this.validLineSegments = new LineSegment[lineSegmentsArrayList.size()];
        // Convert the line segments Array list to array.
        for (int i = 0; i < lineSegmentsArrayList.size(); i++) {
            this.validLineSegments[i] = lineSegmentsArrayList.get(i);
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.totalSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(this.validLineSegments, 3);
    }

    public static void main(String[] args) {

        Point[] mypts = new Point[4];
        mypts[0] = new Point(1, 0);
        mypts[1] = new Point(1, 1);
        mypts[2] = new Point(2, 2);
        mypts[3] = new Point(3, 3);

        FastCollinearPoints collinear = new FastCollinearPoints(mypts);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     // segment.draw();
        // }

        // // read the n points from a file
        // In in = new In(args[0]);
        // int n = in.readInt();
        // Point[] points = new Point[n];
        // for (int i = 0; i < n; i++) {
        //     int x = in.readInt();
        //     int y = in.readInt();
        //     points[i] = new Point(x, y);
        // }
        //
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
            if (points[i] == null)
                throw new IllegalArgumentException("Element " + i + "is null");
        }
        // Check for duplicates
    }
}
