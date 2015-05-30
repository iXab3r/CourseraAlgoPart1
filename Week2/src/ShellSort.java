import java.util.Arrays;
import java.util.Comparator;

public class ShellSort {


    public static void sort(Comparable[] _array, boolean _dumpSteps) {
        if (_dumpSteps)
        {
            System.out.printf("ShellSort:\r\n");
        }
        if (_array.length <= 1) {
            return;
        }

        int h = 1;
        while (h < _array.length) h = h*3 + 1;

        while (h >= 1)
        {
            System.out.printf("%d-sorting:\r\n",h);

            for (int i = h; i < _array.length; i++) {
                if (_dumpSteps)
                {
                    System.out.printf("[I=%d] %s\n", i, Arrays.toString(_array).replace(',',' '));
                }
                int minIndex = i;

                for (int j = i-h; j >= 0; j -= h)
                {
                    if (less(_array[minIndex],_array[j]))
                    {
                        exch(_array, j,minIndex);
                        minIndex = j;
                    }
                }
            }

            h = (h - 1) / 3;
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


