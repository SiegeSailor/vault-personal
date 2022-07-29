import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
     * @return {@code LinkedList<String>} A linked list contain all words.
     */
    public LinkedList<String> parse(String pathname) {
        LinkedList<String> linkedListOfString = new LinkedList<String>();
        try {
            // Read the source file within class folder.
            File file = new File(System.getProperty("user.dir") + "/../" + pathname);

            Scanner scanner = new Scanner(file);
            // In case if the file is empty.
            if (!file.exists()) {
                scanner.close();
                throw new FileNotFoundException();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Separate the line into words.
                String[] arrayOfWord = line.split(" ");
                for (String word : arrayOfWord) {
                    // Remove any non-letter or non-number character.
                    String lowercase = word.toLowerCase();
                    String value = "";
                    for (Character character : lowercase.toCharArray()) {
                        if (Character.isLetterOrDigit(character))
                            value += character;
                    }

                    // Append to the linked list if there is a valid word.
                    if (value.length() > 0)
                        linkedListOfString.append(value);
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

        return linkedListOfString;
    }
}
