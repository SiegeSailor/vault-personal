/*
 * Binary Trees                                                                       *
 *     You are to write a program that reads in a body of text and inserts the text   *
 *     into a binary tree class that you write from scratch based on alphabetical     *
 *     order. You should keep an instance count for each word in the text. Each time  *
 *     a duplicate word is added you should be able to update the instance count for  *
 *     that word. At the end of the text insertion you will show statistics for the   *
 *     run.                                                                           *
 *                                                                                    *
 * - Author:     Zhang, Jin Yu                                                        *
 * - Email:      siege@bu.edu                                                         *
 * - Date:       Aug 4, 2022                                                          *
 * - Class:      MET CS342                                                            *
 * - Instructor: Vic Berry                                                            *
 */

/**
 * Driver.
 * 
 * @author Zhang, Jin Yu
 */
public class Entry {
    /** Binary tree that contains all words. */
    private BinaryTree<String> tree;

    /** Construct the main class and parse the source text into the binary tree. */
    public Entry() {
        this.tree = Parser.parse("source.txt");
    }

    /** Action for detail 1. */
    public void printOne() {
        System.out.println("DETAIL 1:");
        String[] arrayOfString = new String[] { "transylvania", "harker", "renfield", "vampire",
                "expostulate" };
        for (String string : arrayOfString) {
            Node<String> node = this.tree.get(string);
            if (node != null)
                System.out.println(string + " appears " + node.getCount() + " times.");
            else
                System.out.println(string + " doesn't exist.");
        }
    }

    /** Action for detail 2. */
    public void printTwo() {
        System.out.println("DETAIL 2:");
        System.out.println("The depth of the tree is " + this.tree.inquireDepth() + ".");
    }

    /** Action for detail 3. */
    public void printThree() {
        System.out.println("DETAIL 3:");
        System.out.println("There are " + this.tree.getCount() + " different words in the tree.");
    }

    /** Action for detail 4. */
    public void printFour() {
        System.out.println("DETAIL 4:");
        System.out.println("The root is " + this.tree.getRoot() + ".");
    }

    /** Action for detail 5. */
    public void printFive() {
        System.out.println("DETAIL 5:");
        System.out.println("Node at deepest level is " + this.tree.inquireLeaves() + ".");
    }

    /** Action for detail 6. */
    public void printSix() {
        System.out.println("DETAIL 6:");
        System.out.println("There are " + this.tree.inquireCount() + " total words in the tree.");
    }

    /** Action for detail 7. */
    public void printSeven() {
        System.out.println("DETAIL 7:");
        System.out.println(this.tree.inquireOccurMost() + " occurs most frequently.");
    }

    /** Action for detail 8. */
    public void printEight() {
        System.out.println("DETAIL 8:");
        System.out.println(
                "First word in pre-order traversal of the tree is "
                        + this.tree.traverse(Order.PRE_ORDER).split(" ")[0]
                        + ".");
    }

    /** Action for detail 9. */
    public void printNight() {
        System.out.println("DETAIL 9:");
        System.out.println(
                "First word in post-order traversal of the tree is "
                        + this.tree.traverse(Order.POST_ORDER).split(" ")[0]
                        + ".");
    }

    /** Action for detail 10. */
    public void printTen() {
        System.out.println("DETAIL 10:");
        System.out.println(
                "First word in in-order traversal of the tree is "
                        + this.tree.traverse(Order.IN_ORDER).split(" ")[0]
                        + ".");
    }

    /**
     * Main static method to place actions to demonstrate the programming
     * assignment.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        Entry entry = new Entry();
        entry.printOne();
        entry.printTwo();
        entry.printThree();
        entry.printFour();
        entry.printFive();
        entry.printSix();
        entry.printSeven();
        entry.printEight();
        entry.printNight();
        entry.printTen();
    }
}
