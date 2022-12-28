/** Node class for linked lists. */
public class Node {
    /** Next node. */
    private Node next;

    /** Content inside the node. */
    private String data;

    /** A counter inside the node. Only meaningful for the parent linked list. */
    private int count;

    /**
     * Construct a node with given data. count will set to 1.
     * 
     * @param data {@code String} A string.
     */
    public Node(String data) {
        this.data = data;
        this.increaseCount();
    }

    /**
     * Construct a node with given data and count.
     * 
     * @param data  {@code String} A string.
     * @param count {@code int} Existing count.
     */
    public Node(String data, int count) {
        this.data = data;
        this.addCount(count);
    }

    /**
     * Get the next node.
     * 
     * @return Next node. Could be null if there is no node being pointed.
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Set next node.
     * 
     * @param node {@code Node} Next node.
     */
    public void setNext(Node node) {
        this.next = node;
    }

    /**
     * Get the data.
     * 
     * @return A string inside the node.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Get the length for the data.
     * 
     * @return A integer which indicates how long the data is.
     */
    public int getLength() {
        return this.data.length();
    }

    /**
     * Add count with desired input.
     * 
     * @param input {@code int} Desired input.
     */
    public void addCount(int input) {
        this.count += input;
    }

    /** Increase count by 1. */
    public void increaseCount() {
        this.addCount(1);
    }

    /**
     * Get count.
     * 
     * @return Number of count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Return a formatted message with given string.
     * 
     * @param input {@code String} A string.
     * @param count {@code int} Existing count.
     * @return Formatted message.
     */
    public static String format(String input, int count) {
        return input + " (count: " + count + ", length: " + input.length() + ")";
    }

    /** Return a string of class name, hashcode, data, count, and length. */
    @Override
    public String toString() {
        return format(this.getData(), this.getCount());
    }
}
