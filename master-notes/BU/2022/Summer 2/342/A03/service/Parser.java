public class Parser {
    /** Constant for error catch. */
    static final String YES = "Y";

    public Parser(String pathname) {
        try {
            /* Read the source file within class folder. */
            File file = new File(System.getProperty("user.dir") + "/../" + pathname);

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

    }
}
