
import org.omg.CORBA.Environment;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new PointComparer();
    private class PointComparer implements  Comparator<Point>
    {
        @Override
        public int compare(Point p1, Point p2) {
            double slopeP1 = Point.this.slopeTo(p1);
            double slopeP2 = Point.this.slopeTo(p2);

            if (slopeP1 > slopeP2)
            {
                return 1;
            }else if (slopeP1 == slopeP2)
            {
                return 0;
            }else
            {
                return -1;
            }
        }
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }


    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y > that.y)
        {
            return 1;
        }else if (this.y < that.y)
        {
            return -1;
        }else
        {
           if (this.x > that.x)
           {
               return 1;
           } else if (this.x < that.x)
           {
               return -1;
           }else
           {
               return 0;
           }
        }
    }


    // slope between this point and that point
    public double slopeTo(Point that) {
        double yDiff = that.y - this.y;
        double xDiff = that.x - this.x;

        if (xDiff == 0 && yDiff == 0)
        {
            return Double.NEGATIVE_INFINITY;
        }

        if (xDiff == 0)
        {
            return Double.POSITIVE_INFINITY;
        }

        if (yDiff == 0)
        {
            return +0.0d;
        }

        double result = yDiff / xDiff;
        return result;
    }



    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        String homeDir = Point.class.getProtectionDomain().getCodeSource().getLocation().toString();
        System.out.printf("Home: %s ",homeDir );
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = homeDir+args[0];
        In in = new In(filename);
        int N = in.readInt();
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}

