import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Fast {

    private static  HashSet<String>  segments;

    private static final boolean isDebug = false;

    public static void main(String[] args)
    {
        segments = new HashSet<String>();
        InitializeGraph();

        String homeDir = "";

        if (isDebug)
        {
            homeDir = Point.class.getProtectionDomain().getCodeSource().getLocation().toString();
        }

        String filename = homeDir+args[0];
        if (isDebug)
        {
            System.out.printf("FileName: %s ",filename );
        }

        ArrayList<Point> points = ReadPoints(filename);

        if (isDebug)
        {
            System.out.printf("Points(%d)\n",points.size());
            for(Point p : points)
            {
                System.out.printf("%s\n",p);

            }
            System.out.printf("\n\n");
        }

        Point[] orderedPoints = new Point[points.size()];
        for (int i = 0; i < points.size();i++)
        {
            orderedPoints[i] = points.get(i);
        }

        for (Point p0 : points)
        {
            Arrays.sort(orderedPoints, p0.SLOPE_ORDER);

            if (isDebug)
            {
                System.out.printf("\tOrderedPoints(origin - %s)\n",p0);
                for(Point p : orderedPoints)
                {
                    System.out.printf("\t\t%s = %f\n",p, p0.slopeTo(p));
                }
                System.out.printf("\n\n");
            }

            ArrayList<Point> pretendents = new ArrayList<Point>();

            double currentSlope = Double.MIN_VALUE;
            for (Point p1 : orderedPoints)
            {
                double slope = p0.slopeTo(p1);
                if (currentSlope != slope)
                {
                    currentSlope = slope;
                    DumpPretendents(pretendents, p0);
                    pretendents.clear();
                }
                pretendents.add(p1);
            }
            DumpPretendents(pretendents, p0);
        }


        DrawGraph();
    }

    private static void DumpPretendents(ArrayList<Point> pretendents, Point p0)
    {
        if (pretendents.size() >= 3)
        {
            pretendents.add(0, p0);
            Point[] pretendentsArray = new Point[pretendents.size()];
            pretendents.toArray(pretendentsArray);
            Arrays.sort(pretendentsArray, new PointsComparerByCompare());

            String segmentInfo = "";

            for (int i = 0; i < pretendentsArray.length; i++)
            {
                Point p = pretendentsArray[i];
                if (i != pretendentsArray.length-1)
                {
                    segmentInfo += p.toString() + " -> ";
                }else
                {
                    segmentInfo += p.toString();
                }
            }

            if (!segments.contains(segmentInfo))
            {
                segments.add(segmentInfo);
                System.out.printf("%s\n",segmentInfo);
                pretendentsArray[0].drawTo(pretendentsArray[pretendentsArray.length-1]);
            }


        }
    }

    private static class PointsComparerByCompare implements Comparator<Point>
    {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.compareTo(o2);
        }
    }

    private static ArrayList<Point> ReadPoints(String _fileName)
    {
        ArrayList<Point> points = new ArrayList<Point>();
        // read in the input
        In in = new In(_fileName);
        int N = in.readInt();
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points.add(new Point(x, y));
        }
        return points;
    }

    private static void InitializeGraph()
    {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
    }

    private static void DrawGraph()
    {
        // display to screen all at once
        StdDraw.show(0);
    }
}
