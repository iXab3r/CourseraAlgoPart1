import junit.framework.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PointCompareToTest extends Assert
{
    public PointCompareToTest(){}

    @DataPoints
    public static Object[][] isEmptyData = new Object[][] {
            { new Point(1,1), new Point(1,1), 0 },
            { new Point(1,2), new Point(1,1), 1 },
            { new Point(1,0), new Point(1,1), -1 },
            { new Point(2,1), new Point(1,1), 1 },
            { new Point(1,1), new Point(2,1), -1 },
    };


    @Theory
    public void testCompareTo(final Object... testData) {
       Point pt1 = (Point)testData[0];
        Point pt2 = (Point)testData[1];
       int expected = (Integer)testData[2];

       int result = pt1.compareTo(pt2);

        assertEquals(expected, result);
    }
}
