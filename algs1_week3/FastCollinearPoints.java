/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segs;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validatePoints(points);
        // Sort by natural order. Uses comparable.
        // Arrays.sort(points);
        // MyMergeSort.sort(points);
        MyMergeSort mergeSorter = new MyMergeSort();
        mergeSorter.sort(points);

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


        // My stuff
        // Arrays.sort(points);
        MyMergeSort mergeSorter = new MyMergeSort();
        mergeSorter.sort(points);
        for (int i = 0; i < points.length; i++) {
            StdOut.println(points[i].toString());
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
    }

    private static class MyMergeSort {

        private void merge(Comparable<Point>[] a, Comparable<Point>[] aux, int lo, int mid,
                           int hi) {
            // assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
            // assert isSorted(a, mid+1, hi); // precondition: a[mid+1..hi] sorted
            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (aux[j].compareTo((Point) aux[i]) < 0) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
            // assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
        }

        private void sort(Comparable<Point>[] a, Comparable<Point>[] aux, int lo, int hi) {
            if (hi <= lo) return; // Used to use it but use below for faster.
            // int CUTOFF = 7; // For some reason, cut off is not used.
            // if (hi <= lo + CUTOFF - 1) {
            //     Insertion.sort(a, lo, hi);
            //     return;
            // }
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            // if (!less(a[mid + 1], a[mid])) return; // This is added to stop if already sorted
            merge(a, aux, lo, mid, hi);
        }

        public void sort(Comparable<Point>[] a) {
            Comparable[] aux = new Comparable[a.length];
            sort(a, aux, 0, a.length - 1);
        }
    }
}
