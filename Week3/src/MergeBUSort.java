import java.util.Arrays;
import java.util.Comparator;

public class MergeBUSort {


    public static void sort(Comparable[] _array) {
        int N = _array.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz)
        {
            for (int lo = 0; lo < N - sz; lo += sz + sz)
            {
                merge(_array, aux, lo, lo+sz-1, Math.min(lo + sz+sz-1, N -1));
            }
        }

    }

    public static void merge(Comparable[] _array, Comparable[] _aux, int _lo, int _mid, int _hi)
    {
        System.out.printf("merge input(lo=%d, mid=%d, hi=%d): %s \n", _lo, _mid, _hi, Arrays.toString(_array).replace(',',' '));

        assert isSorted(_array, _lo, _mid);
        assert isSorted(_array, _mid+1, _hi);

        for (int i = _lo; i <= _hi; i++)
        {
            _aux[i] = _array[i];
        }


        int i = _lo;
        int j = _mid+1;
        for (int k = _lo; k <= _hi; k++)
        {
            Comparable leftElement = i > _mid ? null : _aux[i];
            Comparable rightElement = j > _hi ? null : _aux[j];
            boolean isLess = leftElement == null ? false : rightElement == null ? true : less(leftElement, rightElement);
            if (isLess)
            {
                System.out.printf("\t\t %s < %s => i++ \n", leftElement, rightElement );
                i++;
                _array[k] = leftElement;
            }else
            {
                System.out.printf("\t\t %s < %s => j++ \n", rightElement, leftElement );

                j++;
                _array[k] = rightElement;
            }
            System.out.printf("\tmerge #[%d], i=%d, j=%d: %s \n", k,i,j, Arrays.toString(_array).replace(',',' '));
        }

        assert isSorted(_aux, _mid+1, _hi);
        System.out.printf("merge output(lo=%d, mid=%d, hi=%d): %s \n\n", _lo, _mid, _hi, Arrays.toString(_array).replace(',',' '));
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


