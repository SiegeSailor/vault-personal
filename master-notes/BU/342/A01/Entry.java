import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;

/*
 * Text Parser                                                                        *
 *     You are to write a program that reads in a body of text and inserts the text   *
 *     into a Linked List class that you write from scratch. You should keep an       *
 *     instance count for each word in the text. Each time a duplicate word is added  *
 *     you should  be able to update the instance count for that word inside the Node *
 *     (this will require you to search the Linked List for each word entered). At    *
 *     the end of the text insertion you will show statistics for the run.            *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       July 13, 2022                                                        *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/** Driver. */
public class Entry {
    /* Pathname for the source text file. */
    static final String PATHNAME = "./source.txt";

    /* Constant for error catch. */
    static final String YES = "Y";

    /** Default constructor. */
    public Entry() {
    }

    /**
     * Actions to demonstrate the programming assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        LinkedList linkedList = new LinkedList();

        try {
            /* Read the source file within class folder. */
            File file = new File(System.getProperty("user.dir") + "/../" + PATHNAME);

            Scanner scanner = new Scanner(file);
            /* In case if the file is empty. */
            if (!file.exists()) {
                scanner.close();
                throw new FileNotFoundException();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                /* Separate the line into words. */
                String[] arrayOfWord = line.split(" ");
                for (String word : arrayOfWord) {
                    /* Remove any non-letter or non-number character. */
                    String lowercase = word.toLowerCase();
                    String value = "";
                    for (Character character : lowercase.toCharArray()) {
                        if (Character.isLetterOrDigit(character))
                            value += character;
                    }

                    /* Append to the main linked list if there is a word after removing step. */
                    if (value.length() > 0)
                        linkedList.append(value);
                }
            }
            scanner.close();
        } catch (Exception event) {
            System.out.println("Something went wrong while reading the file.");
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Do you want to print stack trace? (" + YES + ")");
            String input = keyboard.nextLine();
            keyboard.close();
            if (input.equals(YES))
                event.printStackTrace();

            System.out.println("System exists.");
            System.exit(0);
        }

        /*
         * Due to magnetic is not in the article and the linked list is designed to only
         * store known word, we have to loop the required array of words first and show
         * a dummy message for the missing word.
         */
        System.out.println("How many times do the following words appear in the text?");
        String[] arrayOfData = { "portrait", "persian", "dorian",
                "experimental", "magnetic" };
        LinkedList linkedListOfSearchResult = linkedList.search(arrayOfData);
        /* Loop the linked list for every data. */
        Iterator<Node> iterator = linkedListOfSearchResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        /* Release space. */
        linkedListOfSearchResult.clear();
        System.out.print("\n");

        /*
         * This print 82002 which is not as same as the answer on blackboard 82003. I
         * believe that is due to I didn't store "magnetic" into the linked list.
         * Words not in the article would not appear in the list.
         */
        System.out.println("How many words in this list?");
        System.out.println(linkedList.getNumberOfData() + "\n");

        System.out.println("How many entries are in the Linked List?");
        System.out.println(linkedList.getNumberOfNode() + " entries in this linked list.\n");

        System.out.println("How many words occur more than 20 times?");
        System.out.println(linkedList.search(20) + "\n");

        System.out.println("Which word occurs most frequently?");
        System.out.println(linkedList.getOccurMost() + "\n");

        System.out.println("Which word is the longest in the book?");
        System.out.println(linkedList.getLongest() + "\n");
    }
}
