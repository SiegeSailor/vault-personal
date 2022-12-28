/*
 * Text Parser                                                                        *
 *     You are to write a program that reads in a body of text and inserts the text   *
 *     into a Queue class that you write from scratch. Each word must be inserted in  *
 *     the Queue irrespective of duplicates (add all words to the queue). At the end  *
 *     of the text insertion you will show statistics for the run.                    *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       July 29, 2022                                                        *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/**
 * Driver.
 * 
 * @author Zhang, Jin Yu
 */
public class Entry {
    /** Queue that contains all words. */
    private Queue<String> queue;

    /** Construct the main class and parse the source text into the queue. */
    public Entry() {
        this.queue = Parser.parse("source.txt");
    }

    /** Demonstrate requirement for detail 1. */
    public void printOne() {
        System.out.println("DETAIL 1:");
        String[] arrayOfString = new String[] { "superhuman", "chiromancy", "unsatisfactory", "percutaneous",
                "discernible" };
        for (String string : arrayOfString) {
            int count = 0;
            // Incase of modifying the original queue, creating a new one by cloning it
            Queue<String> queue = this.queue.clone();
            Node<String> cache = queue.getHead();
            boolean isFound = false;
            // Remove the head from an unmodified queue
            while (cache != null) {
                if (cache.getData().equals(string)) {
                    isFound = true;
                    break;
                }

                cache = cache.getNext();
                count++;
            }
            System.out.println(
                    isFound ? "Removed " + count + " times to reach " + string + "."
                            : string + " is not in the article.");
        }
    }

    /** Demonstrate requirement for detail 2. */
    public void printTwo() {
        System.out.println("DETAIL 2:");
        System.out.println("There are " + this.queue.getCount() + " entries in the queue.");
    }

    /** Demonstrate requirement for detail 3. */
    public void printThree() {
        System.out.println("DETAIL 3:");
        Queue<String> queue = this.queue.clone();
        HashMap<String> hashMap = new HashMap<String>();
        // Add the first 1000 entries into the hash map
        for (int count = 0; count < 1000; count++)
            hashMap.add(queue.dequeue().getData());

        // The hash map is consists with lists of lists
        Node<LinkedList<String>> cache = hashMap.getHead();
        int count = 0;
        while (cache != null) {
            if (cache.getData().getCount() > 20)
                count++;
            cache = cache.getNext();
        }
        System.out.println("There are " + count + " words appear more than 20 times.");
    }

    /** Demonstrate requirement for detail 4. */
    public void printFour() {
        System.out.println("DETAIL 4:");
        Queue<String> queue = this.queue.clone();
        HashMap<String> hashMap = new HashMap<String>();
        // Remove all entries from the queue
        while (!queue.seeEmpty())
            hashMap.add(queue.dequeue().getData());

        Node<LinkedList<String>> cache = hashMap.getHead();
        Node<LinkedList<String>> occurMost = null;
        while (cache != null) {
            // Handle first one
            if (occurMost == null) {
                occurMost = cache;
                continue;
            }

            // Compare two list's count
            if (cache.getData().getCount() > occurMost.getData().getCount())
                occurMost = cache;
            cache = cache.getNext();
        }
        System.out.println(occurMost.getData().getHead().getData() + " occurs must frequently.");
    }

    /** Demonstrate requirement for detail 5. */
    public void printFive() {
        System.out.println("DETAIL 5:");
        Queue<String> queue = this.queue.clone();
        Node<String> longest = queue.dequeue();
        // Remove all entries from the queue
        while (!queue.seeEmpty()) {
            Node<String> head = queue.dequeue();
            if (head.getData().length() > longest.getData().length())
                longest = head;
        }
        System.out.println(longest.getData() + " is the longest word.");
    }

    /**
     * Actions to demonstrate the programming assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Entry entry = new Entry();
        entry.printOne();
        entry.printTwo();
        entry.printThree();
        entry.printFour();
        entry.printFive();
    }
}
