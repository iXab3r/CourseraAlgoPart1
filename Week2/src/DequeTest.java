import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException.*;
import org.junit.rules.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() throws Exception {
        new Deque<Object>().addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNull() throws Exception {
        new Deque<Object>().addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextIterationWithNoNodes() throws Exception {
        Deque<Object> q = new Deque<Object>();
        Iterator<Object> iter = q.iterator();
        iter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextIterationWithNoNext() throws Exception {
        Deque<Object> q = new Deque<Object>();
        q.addFirst(1);
        Iterator<Object> iter = q.iterator();
        iter.next();
        iter.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveInIterator() throws Exception {
        Deque<Object> q = new Deque<Object>();
        q.addFirst(1);
        Iterator<Object> iter = q.iterator();
        iter.remove();
    }

    @Test
    public void testIteratorHasNext() throws Exception {
        Deque<Object> q = new Deque<Object>();
        q.addFirst(0);
        Iterator<Object> iter = q.iterator();
        assertEquals(true, iter.hasNext());
    }

    @Test
    public void testAddFirst() throws Exception {
        Deque<Object> q = new Deque<Object>();
        q.addFirst(0);
        q.addFirst(1);
        Iterator<Object> iter = q.iterator();
        assertEquals(1, iter.next());
        assertEquals(0, iter.next());
    }

    @Test
    public void testAddLast() throws Exception {
        Deque<Object> q = new Deque<Object>();
        q.addLast(0);
        q.addLast(1);
        Iterator<Object> iter = q.iterator();
        assertEquals(0, iter.next());
        assertEquals(1, iter.next());
    }

    @Test
    public void testDequeSize() throws Exception {
        Deque<Object> q = new Deque<Object>();
        q.addFirst(0);
        q.addFirst(1);
        assertEquals(2, q.size());
    }
}