/*
 * 8 Queens Problem                                                                   *
 *     You are to write a program that solves the 8 queens problem.                   *
 *     The 8 queens problem postulates that you can place 8 queens on a chess board,  *
 *     so that no queen could capture any other. You will use a stack to solve this   *
 *     problem. On a standard chess board such as below left, a queen can move as     *
 *     shown. (Queen can travel to any of the gray squares)                           *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       July 22, 2022                                                        *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/**
 * Driver.
 * 
 * @author Zhang, Jin YU
 */
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
        Board board = new Board();
        board.start();
    }
}