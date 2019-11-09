/* *****************************************************************************
 *  Name: pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
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

        // Check if root is empty
        if (this.rootNode == null) {
            this.rootNode = new KdNode(p);
            ++this.kdSize;
            return;
        }

        // If root is not empty then start figuring out where to place the new node
        compareNode(rootNode, new KdNode(p), true);
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
        // initialize the two data structures with point from file
        String filename = args[0];
        In in = new In(filename);
        // PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            // for (int i = 0; i < 200; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            // brute.insert(p);
        }
    }


    // My stuff
    private void compareNode(KdNode node, KdNode newNode, boolean compareHorizontalX) {
        // Which direction should we take?
        boolean goLeftOrBottom;
        if (compareHorizontalX) {  // You may need to include this as part of the node. do not want to repeat.
            if (newNode.point.x() < node.point.x()) {
                goLeftOrBottom = true;
            }
            else {
                goLeftOrBottom = false;
            }
        }
        else {
            if (newNode.point.y() < node.point.y()) { // How to deal with tie breakers
                goLeftOrBottom = true;
            }
            else {
                goLeftOrBottom = false;
            }
        }

        // If child is empty then set here otherwise start search again
        if (node.getChildNode(goLeftOrBottom) == null) {
            node.setChildNode(goLeftOrBottom, newNode);
            ++this.kdSize;
        }
        else {
            compareNode(node.getChildNode(goLeftOrBottom), newNode, !compareHorizontalX);
        }
    }

    // Private node class
    private static class KdNode {
        private Point2D point;
        private KdNode leftOrBottomNode;        // the left/bottom subtree
        private KdNode rightOrTopNode;        // the right/top subtree

        public KdNode(Point2D p) {
            if (p == null) throw new IllegalArgumentException("Arg is null");
            this.point = p;
            this.leftOrBottomNode = null;
            this.rightOrTopNode = null;
        }

        public void setChildNode(boolean setLeftOrBottom, KdNode newNode) {
            if (setLeftOrBottom)
                this.leftOrBottomNode = newNode;
            else
                this.rightOrTopNode = newNode;
        }

        public KdNode getChildNode(boolean getLeftOrBottom) {
            if (getLeftOrBottom)
                return this.leftOrBottomNode;
            else
                return this.rightOrTopNode;
        }
    }
}
