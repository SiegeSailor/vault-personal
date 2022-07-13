import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    static final String PATHNAME = "./source.txt";

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
            File file = new File(System.getProperty("user.dir") + "/../" + PATHNAME);

            Scanner scanner = new Scanner(file);
            if (!file.exists()) {
                scanner.close();
                throw new FileNotFoundException();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                /** Parse line and store them into the list */
                String[] arrayOfWord = line.split(" ");
                for (String word : arrayOfWord) {
                    String lowercase = word.toLowerCase();
                    String value = "";
                    for (Character character : lowercase.toCharArray()) {
                        if (Character.isLetterOrDigit(character))
                            value += character;
                    }

                    if (value.length() > 0)
                        linkedList.append(value);
                }
            }
            scanner.close();
        } catch (Exception event) {
            System.out.println(event.getMessage());
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Do you want to print tack trace? (" + YES + ")");
            String input = keyboard.nextLine();
            keyboard.close();
            if (input.equals(YES))
                event.printStackTrace();

            System.out.println("System exists.");
            System.exit(0);
        }

        System.out.println("How many times do the following words appear in the text?");
        System.out.println(linkedList.search("portrait", "persian", "dorian",
                "experimental", "magnetic"));

        System.out.println("How many entries are in the Linked List?");
        System.out.println(linkedList.getNumberOfNode() + " entries in this linked list.");

        System.out.println("How many words occur more than 20 times?");
        System.out.println(linkedList.search(20));

        System.out.println("Which word occurs most frequently?");
        System.out.println(linkedList.getOccurMost());

        System.out.println("Which word is the longest in the book?");
        System.out.println(linkedList.getLongest());
    }
}
