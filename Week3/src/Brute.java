import java.util.ArrayList;

public class Brute {

    private static final boolean isDebug = false;

    public static void main(String[] args)
    {
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

        for(Point p : points)
        {
            p.draw();
        }

        for(Point p0 : points)
        {
            for(Point p1 : points)
            {
                if (p1 == p0)
                {
                    continue;
                }

                if (p1.compareTo(p0) <= 0)
                {
                    continue;
                }

                double lineSlope = p0.slopeTo(p1);

                for(Point p2 : points)
                {
                    if (p2 == p0 || p1 == p2)
                    {
                        continue;
                    }

                    if (p2.slopeTo(p0) == lineSlope && p2.compareTo(p1) > 0)
                    {
                        for (Point p3 : points)
                        {
                            if (p3 == p2 || p3 == p1 || p3 == p0)
                            {
                                continue;
                            }

                            if (p3.slopeTo(p0) == lineSlope && p3.compareTo(p2) > 0)
                            {
                                System.out.printf("%s -> %s -> %s -> %s\n", p0, p1, p2, p3);
                                p0.drawTo(p3);
                            }
                        }
                    }

                }
            }
        }

        DrawGraph();
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
