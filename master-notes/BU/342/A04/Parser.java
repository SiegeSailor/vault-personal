import java.util.Scanner;

import edu.bu.met.cs342.TextParser;

/**
 * Parser class.
 * 
 * @author Zhang, Jin Yu
 */
public class Parser {
    /** Constant for error catch. */
    private static final String YES = "Y";

    /** Default constructor. */
    public Parser() {
    }

    /**
     * Parse the desired file and tear down line of texts into strings without
     * special characters and spaces.
     * 
     * @param pathname {@code String} Pathname for desired file.
     * @return {@code BinaryTree<String>} A linked list contain all words.
     */
    public static BinaryTree<String> parse(String pathname) {
        BinaryTree<String> tree = new BinaryTree<String>();
        try {
            TextParser parser = new TextParser();
            boolean isSuccessful = parser.openFile(pathname);
            if (!isSuccessful)
                throw new Exception("Open file failed.");

            // Read till null.
            while (true) {
                String word = parser.getNextWord();
                if (word != null)
                    tree.add(word);
                else
                    break;
            }
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

        return tree;
    }
}