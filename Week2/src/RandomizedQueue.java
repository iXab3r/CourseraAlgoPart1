import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] m_items;

    private int m_itemsCount = 0;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
    }

    private RandomizedQueue(Item[] _elements, int _elementsCount)                 // construct an empty randomized queue
    {
        m_items = _elements;
        m_itemsCount = _elementsCount;
    }

    private void resizeArray(int _newLength)
    {
        Item[] newArray = (Item[]) new Object[_newLength];
        for (int i = 0; i < m_itemsCount; i++)
        {
            newArray[i] = m_items[i];
            m_items[i] = null;
        }
        m_items = newArray;
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return m_itemsCount == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return m_itemsCount;
    }

    public void enqueue(Item _item)           // add the item
    {
        if (_item == null)
        {
            throw new NullPointerException();
        }
        if (m_items == null || m_itemsCount >= m_items.length)
        {
            resizeArray(m_items == null ? 1 : m_items.length * 2);
        }
        m_items[m_itemsCount] = _item;
        m_itemsCount++;

    }

    public Item dequeue()                    // delete and return a random item
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(m_itemsCount);
        Item itemToReturn = m_items[randomIndex];

        m_itemsCount--;
        if (randomIndex != m_itemsCount)
        {
            // replacing removed item with last
            m_items[randomIndex] = m_items[m_itemsCount];
        }
        m_items[m_itemsCount] = null;

        if (m_itemsCount == 0)
        {
            m_items = null;
        }else if (m_itemsCount <= m_items.length*1/4)
        {
            resizeArray(m_items.length / 2);
        }
        return itemToReturn;
    }

    public Item sample()                     // return (but do not delete) a random item
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(m_itemsCount);
        return m_items[randomIndex];
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new BagIterator(m_items, m_itemsCount);

    }

    public static void main(String[] args)   // unit testing
    {
    }

    private class BagIterator implements Iterator<Item>
    {
        private RandomizedQueue<Item> m_bag;

        public BagIterator(Item[] _elements, int _elementsCount)
        {
            if (_elements == null)
            {
                m_bag = new RandomizedQueue<Item>();
            }else
            {
                _elements = _elements.clone();
                m_bag = new RandomizedQueue<Item>(_elements, _elementsCount);
            }
        }

        @Override
        public boolean hasNext() {
            return !m_bag.isEmpty();
        }

        @Override
        public Item next() {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            return m_bag.dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}