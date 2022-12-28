/*
 * Linked List                                                                        *
 *     Write the program designed in question 1, as a Generic Linked list             *
 *     application, show the code running as an Integer, and String based list.       *
 *     Turn in source code and an output from a run for the program                   *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       July 14, 2022                                                        *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/** Driver. */
public class Entry {
    static final String REGEX_SQUARE_BRACKETS = "\\[[^\\[]*\\]";

    /** Default constructor. */
    public Entry() {
    }

    /**
     * A util to extract useful information from a linked list.
     * 
     * @param <T>        {@code T} Generic type for the input linked list.
     * @param linkedList {@code LinkedList<T>} The linked list.
     * @return A string with information from the linked list.
     */
    public static <T> String format(LinkedList<T> linkedList) {
        return "Loop through the whole linked list: " + linkedList + "\n" +
                "The head node: " + linkedList.getHead() + "\n" +
                "The tail node: " + linkedList.getTail() + "\n" +
                "Empty: " + linkedList.isEmpty() + "\n";
    }

    /**
     * Print required information from a linked list with given array of data.
     * 
     * @param <T>         {@code T} Generic type for the input linked list.
     * @param arrayOfData {@code T[]} Array of data.
     */
    public static <T> void wrapPrint(T[] arrayOfData) {
        LinkedList<T> linkedList = new LinkedList<T>(arrayOfData);
        System.out
                .println("\n=== " + arrayOfData.getClass().getSimpleName().replaceAll(REGEX_SQUARE_BRACKETS, "")
                        + " Linked List ===\n" + format(linkedList));
        System.out.println("Delete the last element.\n");
        linkedList.delete();
        System.out.println(format(linkedList));
        System.out.println("Clear the whole linked list.\n");
        linkedList.clear();
        System.out.println(format(linkedList));
    }

    /**
     * Actions to demonstrate the programming assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Integer[] arrayOfInteger = { 0, 1, 2, 3, 4, 5 };
        wrapPrint(arrayOfInteger);
        String[] arrayOfString = { "a", "b", "c", "d", "e" };
        wrapPrint(arrayOfString);
    }
}
