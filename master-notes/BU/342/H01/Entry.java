/*
 * I am thinking of a number                                                          *
 *     Plays the game I am thinking of a number between 1 and 1000.                   *
 *     The computer will think of the number, and the user will repeatedly            *
 *     guess until they answer correctly.                                             *
 *     The program will tell the user if guesses are too high or too low.             *
 *                                                                                    *
 *     When the user guesses the correct number, the game should show the user how    *
 *     many turns it took. You must handle incorrect input (letters when numbers were *
 *     expected).                                                                     *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       July 12, 2022                                                        *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/** Driver. */
public class Entry {
    /** Default constructor. */
    public Entry() {
    }

    /**
     * Actions to demonstrate the homework.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Game game = new Game();
        game.start();
    }
}
