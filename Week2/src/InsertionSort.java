import java.util.Arrays;
import java.util.Comparator;

public class InsertionSort {


    public static void sort(Comparable[] _array, boolean _dumpSteps) {
        if (_dumpSteps)
        {
            System.out.printf("InsertionSort:\r\n");
        }
        if (_array.length <= 1) {
            return;
        }


        for (int i = 1; i < _array.length; i++) {
            int minIndex = i;
            if (_dumpSteps)
            {
                System.out.printf("[I=%d] %s\n", i, Arrays.toString(_array).replace(',',' '));
            }
            for (int j = i-1; j >= 0; j--) {
                if (less(_array[minIndex], _array[j]))
                {
                    exch(_array, j, minIndex);
                    if (_dumpSteps)
                    {
                        System.out.printf("\t[J=%d] %s\n", j, Arrays.toString(_array).replace(',',' '));
                    }
                    minIndex = j;
                }
            }
        }
        if (_dumpSteps)
        {
            System.out.printf("Result: %s\r\n\r\n", Arrays.toString(_array).replace(',',' '));
        }
    }
        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param _array the array to be sorted
         */
    public static void sort(Comparable[] _array) {
        sort(_array, false);
    }

    /**
     * ********************************************************************
     * Helper sorting functions
     * *********************************************************************
     */

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // exchange a[i] and a[j]  (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


}


