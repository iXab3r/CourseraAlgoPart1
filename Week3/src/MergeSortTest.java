import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void testMerge() throws Exception {
          Integer[] input = new Integer[]{2, 3, 1, 4};
        Integer[] expected = new Integer[]{1,2, 3,4};

        Integer[] temp=  new Integer[input.length];
          MergeSort.merge(input,temp,0,  (input.length-1)/2 , input.length-1);

        assertArrayEquals(expected, input);
    }
}