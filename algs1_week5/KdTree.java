/* *****************************************************************************
 *  Name: pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private KdNode rootNode;
    private int kdSize;

    // construct an empty set of points
    public KdTree() {
        this.rootNode = null;
        this.kdSize = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.kdSize == 0;
    }

    // number of points in the set
    public int size() {
        return this.kdSize;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Arg is null");
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Arg is null");
        return true;
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Arg is null");
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Arg is null");
        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }


    // My stuff

    private static class KdNode {
        private Point2D point;
        private KdNode leftOrBottomNode;        // the left/bottom subtree
        private KdNode rightOrTopNode;        // the right/top subtree

        public KdNode(Point2D p) {
            if (p == null) throw new IllegalArgumentException("Arg is null");
            this.point = p;
        }
    }
}
