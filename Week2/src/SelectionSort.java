import java.util.Arrays;
import java.util.Comparator;

public class SelectionSort {


    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param _array the array to be sorted
     */
    public static void sort(Comparable[] _array, boolean _dumpSteps) {
        if (_dumpSteps) {
            System.out.printf("InsertionSort:\r\n");
        }
        if (_array.length <= 1) {
            return;
        }
        for (int i = 0; i < _array.length; i++) {
            if (_dumpSteps) {
                System.out.printf("[I=%d] %s\n", i, Arrays.toString(_array).replace(',',' '));
            }
            int minIndex = i;
            for (int j = i + 1; j < _array.length; j++) {
                if (_array[j].compareTo(_array[minIndex]) < 0) {
                    minIndex = j;

                }
            }
            exch(_array, i, minIndex);
            if (_dumpSteps) {
                System.out.printf("\t[J=%d] %s\n", minIndex, Arrays.toString(_array).replace(',',' '));
            }
        }
        if (_dumpSteps) {
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


