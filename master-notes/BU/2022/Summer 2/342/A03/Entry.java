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
    /** Default constructor. */
    public Entry() {
    }

    /**
     * Actions to demonstrate the programming assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Queue<String> queueOfWord = Parser.parse("source.txt");

        // Detail 1
        System.out.println("DETAIL 1:");
        String[] arrayOfString = new String[] { "superhuman", "chiromancy", "unsatisfactory", "percutaneous",
                "discernible" };
        for (String string : arrayOfString) {
            int count = 0;
            Queue<String> queue = queueOfWord.clone();
            Node<String> cache = queue.getHead();
            boolean isFound = false;
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

        // Detail 2
        System.out.println("DETAIL 2:");
        System.out.println("There are " + queueOfWord.getCount() + " entries in the queue.");

        // Detail 3
        System.out.println("DETAIL 3:");
        Queue<String> queue = queueOfWord.clone();
        int count = 0;
        while (!queue.seeEmpty()) {
            if (queue.dequeue().getCount() > 20)
                count++;
        }
        System.out.println("There are " + count + " words appear more than 20 times.");

        // Detail 4
        System.out.println("DETAIL 4:");
        Queue<String> queueOfForth = queueOfWord.clone();
        Node<String> nodeOccurMost = queueOfForth.dequeue();
        while (!queueOfForth.seeEmpty()) {
            Node<String> head = queueOfForth.dequeue();
            if (head.getCount() > nodeOccurMost.getCount())
                nodeOccurMost = head;
        }
        System.out.println(nodeOccurMost);

        // Detail 5
        System.out.println("DETAIL 5:");
        Queue<String> queueOfFifth = queueOfWord.clone();
        Node<String> nodeLongest = queueOfFifth.dequeue();
        while (!queueOfFifth.seeEmpty()) {
            Node<String> head = queueOfFifth.dequeue();
            if (head.getData().length() > nodeLongest.getData().length())
                nodeLongest = head;
        }
        System.out.println(nodeLongest);
    }
}
