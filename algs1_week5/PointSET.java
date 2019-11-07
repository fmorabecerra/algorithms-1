/* *****************************************************************************
 *  Name: pancho
 *  Date:
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        this.points = new TreeSet<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return this.points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        this.points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return this.points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : this.points)
            point.draw();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (this.isEmpty()) return null;
        ArrayList<Point2D> pointsInRectangle = new ArrayList<Point2D>();
        for (Point2D point : this.points)
            if (rect.contains(point)) pointsInRectangle.add(point);
        return pointsInRectangle;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (this.isEmpty()) return null;
        Point2D closestPoint = this.points.first();
        for (Point2D point : this.points) {
            if (p.distanceTo(point) > p.distanceTo(closestPoint))
                closestPoint = point;
        }
        return closestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
