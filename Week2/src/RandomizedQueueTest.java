import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    ExpectedException exception = ExpectedException.none();

    @Test
    public void isEmptyTrueOnCtor() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        assertTrue(bag.isEmpty());
    }

    @Test
    public void isEmptyFalseAfterEnqueue() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        bag.enqueue(0);
        assertFalse(bag.isEmpty());
    }

    @Test
    public void isEmptyTrueAfterEnqueueDequeue() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        bag.enqueue(0);
        bag.dequeue();
        assertTrue(bag.isEmpty());
    }

    @Test
    public void SizeIsZeroAfterCtor() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        assertEquals(0, bag.size());
    }

    @Test
    public void SizeIsCorrectAfterEnqueue() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        bag.enqueue(0);
        assertEquals(1, bag.size());
    }

    @Test
    public void SizeIsCorrectAfterEnqueueDequeue() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        bag.enqueue(0);
        bag.dequeue();
        assertEquals(0, bag.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void ThrowsExceptionOnEmptyDequeue() throws Exception {
        exception.expect(NoSuchElementException.class);
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        bag.dequeue();
    }

    @Test
    public void EnqueueSingleEnqueueDequeueWorks() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        int value = 10;
        bag.enqueue(value);

        int result = bag.dequeue();
        assertEquals(value, result);
    }

    @Test
    public void EnqueueMultipleEnqueueDequeueWorks() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();

        int[] expected = new int[10];

        for (int i = 0; i < expected.length; i++)
        {
            bag.enqueue(i);
            expected[i] = i;
        }

        int[] result = new int[expected.length];
        for (int i = 0; i < expected.length; i++)
        {
            int item = bag.dequeue();
            result[i] = item;
        }

        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void DequeueTestResize() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();

        bag.enqueue(0); // 1\1
        AssertBagInnerLength(bag, 1);

        bag.enqueue(0); // 2\2
        AssertBagInnerLength(bag, 2);

        bag.enqueue(0); // 3\4
        AssertBagInnerLength(bag, 4);

        bag.enqueue(0); // 4\4
        AssertBagInnerLength(bag, 4);

        bag.enqueue(0); // 5\8
        AssertBagInnerLength(bag, 8);


        bag.dequeue(); // 4\8
        AssertBagInnerLength(bag, 8);

        bag.dequeue(); // 3\8
        AssertBagInnerLength(bag, 8);

        bag.dequeue(); // 2\4
        AssertBagInnerLength(bag, 4);

        bag.dequeue(); // 1\2
        AssertBagInnerLength(bag, 2);

        bag.dequeue(); // 0\1
    }

    private static <T> void AssertBagInnerLength(RandomizedQueue<T> _bag, int _expectedLength) throws Exception
    {
        Field f = _bag.getClass().getDeclaredField("m_items"); //NoSuchFieldException
        f.setAccessible(true);
        Object[] itemsArray;
        itemsArray = (Object[]) f.get(_bag); //IllegalAccessException
        assertEquals(_expectedLength, itemsArray.length);

    }

    @Test
    public void SampleReturnsSingleValue() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();

        int value = 10;
        bag.enqueue(value);

        int result = bag.sample();

        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());
        assertEquals(value, result);
    }

    @Test
    public void IteratorSequentalAccess() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();

        int[] expected = new int[10];
        for (int i = 0; i < expected.length; i++)
        {
            bag.enqueue(i);
            expected[i] = i;
        }

        int[] result = new int[expected.length];
        int resultIndex = 0;
        for(Integer value : bag)
        {
            result[resultIndex] = value;
            resultIndex++;
        }

        assertFalse(bag.isEmpty());
        assertEquals(expected.length, bag.size());
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void IteratorOverEmptyBag() throws Exception {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();

        boolean noValue = true;
        for(Integer ignored : bag)
        {
            noValue = false;
        }
        assertTrue(noValue);
    }

}