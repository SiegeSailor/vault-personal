import java.util.Scanner;

/** Game class. */
public class Game {
    /** The number of played games. */
    public static int numberOfGame;

    /** Maximum target value. */
    static final int MAX = 1000;

    /** Minium target value. */
    static final int MIN = 1;

    /** Current target value. */
    private int target;

    /** Current guessed lowest value. */
    private int min;

    /** Current guessed highest value. */
    private int max;

    /** The number of guesses. */
    private int count;

    /** Indicate if the current game is ended. */
    private boolean isEnded;

    /** Input stream. */
    private Scanner keyboard;

    /**
     * Generate a random integer between MAX and MIN.
     * 
     * @return A random integer between MAX and MIN.
     */
    private int randomize() {
        return (int) Math.floor(Math.random() * (MAX - MIN + 1) + MIN);
    }

    /**
     * End a game. Let user decide whether start another round or end the whole
     * game. The number of game played will display if decide not to start again.
     */
    private void end() {
        this.isEnded = true;
        System.out.println("The game is over. Would you like to start another round? (Y)");
        String input = keyboard.nextLine();
        if (input.equals("Y"))
            this.start();
        else
            System.out.println("The game is closed. Thank you for participating in our event. You have played "
                    + numberOfGame + " times.");
    };

    /** Default constructor. */
    public Game() {
    }

    /**
     * Start a game. Initialize data such as number of guesses, guessed lowest and
     * highest value, and the target value. The number of played game will increase
     * right after starting a new game even if user never guessed.
     */
    public void start() {
        numberOfGame++;
        this.keyboard = new Scanner(System.in);
        this.min = MIN;
        this.max = MAX;
        this.target = this.randomize();
        this.isEnded = false;

        System.out.println(
                "\nThe game I am thinking of a number (" + numberOfGame + "th round"
                        + ") has begun. The target value is between " + MIN
                        + " to " + MAX + ".");

        while (!this.isEnded) {
            System.out.println(
                    "Please enter an integer within the range " + this.min + "-" + this.max
                            + ". Input STOP to give up:");
            this.count++;
            String input = keyboard.nextLine();

            /** Option to give up. Target value and number of guesses will display. */
            if (input.equals("STOP")) {
                System.out.println(
                        "You gave up. The target value was " + this.target + ". You guessed " + this.count + " times.");
                this.end();
                break;
            }

            /** Parse if user inputs a valid value. */
            int value;
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException event) {
                System.out.println("The entered value is not a integer, please try again. (This guess doesn't count)");
                this.count--;
                continue;
            }

            if (value == this.target) {
                System.out.println("Congratulation! The target value is indeed " + value + "! It took you " + this.count
                        + " times to guess.");
                this.end();
                break;
            }

            if (value < this.target) {
                System.out.println("The target value is bigger.");
                /**
                 * Only update min when the input value is bigger.
                 * For example, if user inputs 50 and min is 60 then min will not be updated.
                 */
                if (value > this.min)
                    this.min = value;
                continue;
            }

            if (value > this.target) {
                System.out.println("The target value is smaller.");
                /**
                 * Only update max when the input value is smaller.
                 * For example, if user inputs 70 and max is 60 then max will not be updated.
                 */
                if (value < this.max)
                    this.max = value;
                continue;
            }
        }
    }
}
