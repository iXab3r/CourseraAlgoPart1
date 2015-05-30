import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class SortTest {

    @Parameterized.Parameters
    public static Collection data() {
        Object[][] data = new Object[][] { { QuickSort.class } };
        return Arrays.asList(data);
    }

    private Method m_sort;

    public SortTest(Class _sorter) throws  Exception
    {
        m_sort = _sorter.getDeclaredMethod("sort", new Class[] {Comparable[].class});
        m_sort.setAccessible(true); //if security settings allow this
    }

    private void SortAndCheck(Comparable[] _array, boolean _showArray) throws Exception
    {
        if (_showArray)
        {
            System.out.printf("Before: ");
            show(_array);
        }
        m_sort.invoke(null, (Object)_array);
        if (_showArray)
        {
            System.out.printf("After: ");
            show(_array);
        }
        assertTrue(isSorted(_array));

    }

    public void PerformanceTest() throws Exception {
        int iterationsCount = 10000;
        int valuesCount = 1000;
        Integer[] values = new Integer[valuesCount];
        for (int i = 0; i < values.length; i++)
        {
            values[i] = 0;
        }


        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < iterationsCount; i++){
            StdRandom.shuffle(values);
            SortAndCheck(values, false);
        }
        double totalTime = sw.elapsedTime();
        System.out.printf("Elapsed(s): %f (%f per iteration)", totalTime, totalTime / iterationsCount);
    }

    @Test
    public void SimpleArray() throws Exception {
        Integer[] values = new Integer[]{1,2,3,4,5,6,7,8,9,0};
        StdRandom.shuffle(values);
        SortAndCheck(values, true);
    }

    @Test
    public void SmallReversedArray() throws Exception {
        Integer[] values = new Integer[]{3,2,1};
        SortAndCheck(values, true);
    }

    @Test
    public void SmallPartlyReversedArray() throws Exception {
        Integer[] values = new Integer[]{3,1,2};
        SortAndCheck(values, true);
    }

    @Test
    public void SmallSortedArray() throws Exception {
        Integer[] values = new Integer[]{1,2,3};
        SortAndCheck(values, true);
    }

    @Test
    public void SmallEvenReverseSortedArray() throws Exception {
        Integer[] values = new Integer[]{4,3,2,1};
        SortAndCheck(values, true);
    }

    @Test
    public void OneElementArray() throws Exception {
        Integer[] values = new Integer[]{1};
        SortAndCheck(values, true);

    }

    @Test
    public void CorderCaseLeftElement() throws Exception {
        Integer[] values = new Integer[]{3,1,2,3,4};
        SortAndCheck(values, true);

    }

    @Test
    public void CorderCaseRightElement() throws Exception {
        Integer[] values = new Integer[]{1,2,4,5,3};
        SortAndCheck(values, true);
    }



    /***********************************************************************
     *  Helper sorting functions
     ***********************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    /***********************************************************************
     *  Check if array is sorted - useful for debugging
     ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator c) {
        return isSorted(a, c, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator c, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(c, a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }


}