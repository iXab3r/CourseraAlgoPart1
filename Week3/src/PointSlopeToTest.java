import junit.framework.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PointSlopeToTest extends Assert
{
    public PointSlopeToTest(){}

    @DataPoints
    public static Object[][] isEmptyData = new Object[][] {
            { new Point(1,1), new Point(1,1), Double.NEGATIVE_INFINITY },
            { new Point(1,1), new Point(2,1), +0.0d },
            { new Point(2,1), new Point(1,1), +0.0d },
            { new Point(1,1), new Point(1,2), Double.POSITIVE_INFINITY },
    };


    @Theory
    public void testCompareTo(final Object... testData) {
       Point pt1 = (Point)testData[0];
        Point pt2 = (Point)testData[1];
       double expected = (Double)testData[2];

       double result = pt1.slopeTo(pt2);

        assertEquals(expected, result);
    }
}
