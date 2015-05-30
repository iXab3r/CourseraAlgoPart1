import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {


    public static void sort(Comparable[] _array) {
        sort(_array, 0, _array.length-1);
    }

    private static void sort(Comparable[] _array, int _lo, int _hi) {
        if (_hi <= _lo)
        {
            return;
        }
        int j = partition(_array, _lo, _hi);
        sort(_array, _lo, j-1);
        sort(_array, j+1, _hi);
    }

    public static int partition(Comparable[] _array, int _lo, int _hi) {
        int i = _lo;
        int j = _hi+1;

        while (true)
        {
            while (less(_array[++i],_array[_lo]))
            {
                if (i == _hi)
                {
                    break;
                }
            }
            while (less(_array[_lo],_array[--j]))
            {
                if (j == _lo)
                {
                    break;
                }
            }

            if (i >= j)
            {
                break;
            }
            exch(_array, i,j);
        }
        exch(_array, _lo,j);

        return j;
    }

    public static int threeWayPartition(Comparable[] _array, int _lo, int _hi) {
        int i = _lo;
        int lt = i;
        int gt = _hi;

        while (true)
        {
           int cmp = _array[i].compareTo(_array[lt]);
            if (cmp == 0)
            {
                i++;
            }else if (cmp > 0)
            {
                exch(_array, gt, i);
                gt--;
            }else
            {
                exch(_array, lt, i);
                lt++;
                i++;
            }

            if (i >= gt)
            {
                break;
            }
        }
        exch(_array, lt,gt);

        return lt;
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


    private static boolean isSorted(Comparable[] _array, int _lo, int _hi)
    {
        for (int i = _lo; i < _hi-1; i++)
        {
            if (!less(_array[i], _array[i+1]))
            {
                return false;
            }
        }
        return true;
    }
}


