/* *****************************************************************************
 *  Name: pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class KdTree {
    private KdNode rootNode;
    private int kdSize;
    private ArrayList<Point2D> pointsInRectangle;

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
            this.rootNode = new KdNode(p, new RectHV(0, 0, 1, 1));
            ++this.kdSize;
            return;
        }

        // If root is not empty then start figuring out where to place the new node
        compareNodeInsert(this.rootNode, new KdNode(p, null), true);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Arg is null");
        if (this.isEmpty()) return false;
        return compareNodeSearch(this.rootNode, new KdNode(p, null), true);
    }

    // draw all points to standard draw
    public void draw() {
        drawRecursively(rootNode, true);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Arg is null");
        this.pointsInRectangle = new ArrayList<Point2D>();
        // Do recursive range search if tree is not empty
        if (!this.isEmpty())
            areSubTreePointsInRect(this.rootNode, rect, true);
        return this.pointsInRectangle;
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
        StdOut.println("Does it contain the point: " + kdtree.contains(new Point2D(0.6, 0.0)));

        RectHV rect = new RectHV(0.25, 0.0, 0.75, 0.625);
        StdOut.println("Rect dimentions" + rect);
        for (Point2D p : kdtree.range(rect))
            StdOut.println("Point in range: " + p);
    }


    // My stuff
    private void areSubTreePointsInRect(KdNode node, RectHV rect, boolean horizontalX) {
        if (node == null) return;
        int whereToLook;
        if (rect.contains(node.point)) {
            this.pointsInRectangle.add(node.point);
            whereToLook = 0; // You have to search both sides
        }
        else
            whereToLook = node.lineIntersets(rect, horizontalX);

        if (whereToLook == 0) {
            areSubTreePointsInRect(node.leftOrBottomNode, rect, !horizontalX);
            areSubTreePointsInRect(node.rightOrTopNode, rect, !horizontalX);
        }
        else
            areSubTreePointsInRect(node.getChildNode(whereToLook < 0), rect, !horizontalX);
    }

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
            node.setChildNode(goLeftOrBottom, newNode, compareHorizontalX);
            ++this.kdSize;
        }
        else  // Do a recursive call
            compareNodeInsert(node.getChildNode(goLeftOrBottom), newNode, !compareHorizontalX);
    }

    // Private node class
    private static class KdNode {
        private final Point2D point;
        private KdNode leftOrBottomNode;        // the left/bottom subtree
        private KdNode rightOrTopNode;        // the right/top subtree
        private RectHV rect;

        public KdNode(Point2D p, RectHV rectangle) {
            if (p == null) throw new IllegalArgumentException("Arg is null");
            this.point = p;
            this.rect = rectangle;
            this.leftOrBottomNode = null;
            this.rightOrTopNode = null;
        }

        public RectHV getChildRect(boolean leftOrBottom, boolean getHorizontalX) {
            if (getHorizontalX) {  // Changing x only
                if (leftOrBottom)  // Changing x max only
                    return new RectHV(rect.xmin(), rect.ymin(), point.x(), rect.ymax());
                else  // Changing x min only
                    return new RectHV(point.x(), rect.ymin(), rect.xmax(), rect.ymax());
            }
            else {  // Changing y only
                if (leftOrBottom)  // Changing y max only
                    return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), point.y());
                else  // Changing y min only
                    return new RectHV(rect.xmin(), point.y(), rect.xmax(), rect.ymax());
            }
        }

        public boolean compareToNode(KdNode node, boolean compareHorizontalX) {
            if (compareHorizontalX)
                return (this.point.x() < node.point.x());
            else
                return (this.point.y() < node.point.y());
        }

        public int lineIntersets(RectHV rect, boolean lookAtHorizontalX) {
            if (lookAtHorizontalX)
                // Rectangle is to the left
                if (rect.xmin() < this.point.x() && rect.xmax() < this.point.x()) return -1;
                    // Rectangle is to the right
                else if (rect.xmin() > this.point.x() && rect.xmax() > this.point.x()) return +1;
                    // Node line intersects rectangle.
                else return 0;
            else
                // Rectangle is below
                if (rect.ymin() < this.point.y() && rect.ymax() < this.point.y()) return -1;
                    // Rectangle is above
                else if (rect.ymin() > this.point.y() && rect.ymax() > this.point.y()) return +1;
                    // Node line intersects rectangle.
                else return 0;
        }

        public void setChildNode(boolean setLeftOrBottom, KdNode newNode, boolean horizontalX) {
            if (setLeftOrBottom) {
                this.leftOrBottomNode = newNode;
                this.leftOrBottomNode.rect = getChildRect(true, horizontalX);
            }
            else {
                this.rightOrTopNode = newNode;
                this.rightOrTopNode.rect = getChildRect(false, horizontalX);
            }
        }

        public KdNode getChildNode(boolean getLeftOrBottom) {
            if (getLeftOrBottom)
                return this.leftOrBottomNode;
            else
                return this.rightOrTopNode;
        }
    }

    private void drawRecursively(KdNode node, boolean drawHorizontalX) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();
        StdDraw.setPenRadius(0.003);
        if (drawHorizontalX) {  // Draw vertical line
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        }
        else {  // Draw horizontal line
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }
        // Now draw children
        if (node.leftOrBottomNode != null)
            drawRecursively(node.leftOrBottomNode, !drawHorizontalX);
        if (node.rightOrTopNode != null)
            drawRecursively(node.rightOrTopNode, !drawHorizontalX);
    }
}
