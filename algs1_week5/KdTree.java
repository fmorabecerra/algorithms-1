/* *****************************************************************************
 *  Name: pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

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
        compareNodeInsert(this.rootNode, new KdNode(p), true);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Arg is null");
        return compareNodeSearch(this.rootNode, new KdNode(p), true);
    }

    // draw all points to standard draw
    public void draw() {
        drawRecursively(rootNode, true);
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
        StdOut.println("Size of tree: " + kdtree.size());
    }


    // My stuff
    // Build based on compareNodeInsert
    private boolean compareNodeSearch(KdNode node, KdNode newNode, boolean compareHorizontalX) {
        if (node.point.equals(newNode.point)) return true;  // Points equal
        boolean goLeftOrBottom = newNode.compareToNode(node, compareHorizontalX);
        // If Child is empty that it was not found
        if (node.getChildNode(goLeftOrBottom) == null) return false;
        else  // Do a recursive call
            return compareNodeSearch(node.getChildNode(goLeftOrBottom), newNode,
                                     !compareHorizontalX);
    }

    private void compareNodeInsert(KdNode node, KdNode newNode, boolean compareHorizontalX) {
        if (node.point.equals(newNode.point)) return;  // Points equal
        boolean goLeftOrBottom = newNode.compareToNode(node, compareHorizontalX);
        // If child is empty then set here otherwise start search again
        if (node.getChildNode(goLeftOrBottom) == null) {
            node.setChildNode(goLeftOrBottom, newNode);
            ++this.kdSize;
        }
        else  // Do a recursive call
            compareNodeInsert(node.getChildNode(goLeftOrBottom), newNode, !compareHorizontalX);
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

        public boolean compareToNode(KdNode node, boolean compareHorizontalX) {
            if (compareHorizontalX)
                return (this.point.x() < node.point.x());
            else
                return (this.point.y() < node.point.y());
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

    private void drawRecursively(KdNode node, boolean drawHorizontalX) {
        node.point.draw();
        // if (drawHorizontalX) {
        //     // Draw horizontal line
        // }
        // else {
        //     // Draw verticle line
        // }
        // Now draw children
        if (node.leftOrBottomNode != null)
            drawRecursively(node.leftOrBottomNode, !drawHorizontalX);
        if (node.rightOrTopNode != null)
            drawRecursively(node.rightOrTopNode, !drawHorizontalX);
    }
}
