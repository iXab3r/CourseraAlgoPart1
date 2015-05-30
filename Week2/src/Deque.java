import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node<Item> m_first;
    private Node<Item> m_last;
    private int m_itemsCount;

    private class Node<T>
    {
        public T Value;
        public Node Next;
        public Node Previous;
    }

    public Deque()                           // construct an empty deque
    {

    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return m_first == m_last && m_first == null;
    }
    public int size()                        // return the number of items on the deque
    {
        return m_itemsCount;
    }

    private void Reinitialize(Item _item)
    {
        m_first = new Node<Item>();
        m_first.Value = _item;

        m_last = new Node<Item>();
        m_last.Previous = m_first;
        m_first.Next = m_last;
    }

    public void addFirst(Item _item)          // insert the item at the front
    {
        if (_item == null)
        {
            throw new NullPointerException();
        }
        if (isEmpty())
        {
           Reinitialize(_item);
        }else
        {
            Node<Item> newFirst = new Node<Item>();
            newFirst.Value = _item;
            newFirst.Next = m_first;

            m_first.Previous = newFirst;
            m_first = newFirst;
        }
        m_itemsCount++;
    }
    public void addLast(Item _item)           // insert the item at the end
    {
        if (_item == null)
        {
            throw new NullPointerException();
        }
        if (isEmpty())
        {
            Reinitialize(_item);
        }else
        {
            Node<Item> newLast = new Node<Item>();
            newLast.Value = _item;
            newLast.Previous = m_last.Previous;
            newLast.Next = m_last;

            m_last.Previous.Next = newLast;
            m_last.Previous = newLast;
        }
        m_itemsCount++;
    }



    public Item removeFirst()                // delete and return the item at the front
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        Item value = m_first.Value;

        if (m_first.Next == m_last)
        {
            Purge();
        }else
        {
            Node<Item> nextItem = m_first.Next;

            nextItem.Previous = null;
            m_first.Next  = null;
            m_first.Previous = null;
            m_first = nextItem;
        }
        m_itemsCount--;
        return value;
    }

    private void Purge()
    {
        m_last.Previous = null;
        m_last.Next = null;
        m_last = null;

        m_first.Previous = null;
        m_first.Next = null;
        m_first = null;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        m_itemsCount--;
        if (m_last.Previous == m_first)
        {
            Item value = m_first.Value;
            Purge();
            return value;
        }else
        {
            Node<Item> nodeToRemove = m_last.Previous;

            m_last.Previous = nodeToRemove.Previous;

            nodeToRemove.Previous.Next = m_last;
            nodeToRemove.Previous = null;
            nodeToRemove.Next = null;

            return nodeToRemove.Value;
        }
    }

    private void Print()
    {
        if (isEmpty())
        {
            StdOut.printf("Empty(%d)\r\n",m_itemsCount);
        }else
        {
            StdOut.printf("%d element(s)\r\n",m_itemsCount);

            Node<Item> item = m_first;
            StdOut.print("[ ");
            while (item.Next != null)
            {
                StdOut.printf(" %s ", item.Value);
                item = item.Next;
            };
            StdOut.print(" ]");

            StdOut.println();
        }

    }

    @Override
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }

    public static void main(String[] args)   // unit testing
    {
        int[] elements = new int[]{ 1,2,3,4,5,6,7,8,9 };

        Deque<Integer> m_deque = new Deque<Integer>();
        m_deque.Print();
        assert m_deque.isEmpty();

        m_deque.addFirst(elements[0]); m_deque.Print();
        m_deque.addFirst(elements[1]); m_deque.Print();
        m_deque.addFirst(elements[2]); m_deque.Print();

        int removedItem = m_deque.removeFirst(); m_deque.Print();
        assert elements[2] == removedItem;

        m_deque.addLast(elements[3]); m_deque.Print();
        m_deque.addLast(elements[4]); m_deque.Print();

        removedItem = m_deque.removeFirst(); m_deque.Print();
        assert elements[1] == removedItem;

        removedItem = m_deque.removeLast(); m_deque.Print();
        assert elements[4] == removedItem;

        m_deque.addFirst(elements[0]); m_deque.Print();
        m_deque.addFirst(elements[0]); m_deque.Print();
        m_deque.addFirst(elements[0]); m_deque.Print();

        m_deque.removeLast(); m_deque.Print();
        m_deque.removeLast(); m_deque.Print();
        m_deque.removeLast(); m_deque.Print();
        m_deque.removeLast(); m_deque.Print();
        m_deque.removeLast(); m_deque.Print();


    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> m_current;

        public DequeIterator()
        {
            m_current = m_first;
        }

        @Override
        public boolean hasNext() {
            return m_current != null && m_current.Next != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            Item value = m_current.Value;
            m_current = m_current.Next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
