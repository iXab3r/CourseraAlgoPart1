import java.util.Comparator;

public class BubbleSort {


    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param _array the array to be sorted
     */
    public static void sort(Comparable[] _array) {
        if (_array.length <= 1) {
            return;
        }
        for (int i = 0; i < _array.length; i++) {
            for (int j = i+1; j < _array.length; j++) {
                if (less(_array[j], _array[i]))
                {
                    exch(_array, i, j);
                }
            }
        }

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


