public class Subset {
    public static void main(String[] args) {
         int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> bag = new RandomizedQueue<>();

        String input;
            while (!StdIn.isEmpty())
        {
            input = StdIn.readString();
            bag.enqueue(input);
        };

        int itemsWritten = 0;
        while (itemsWritten < k)
        {
            StdOut.printf("%s",bag.dequeue());

            itemsWritten++;
            if (itemsWritten < k)
            {
                StdOut.println();
            }
        }
    }
}
