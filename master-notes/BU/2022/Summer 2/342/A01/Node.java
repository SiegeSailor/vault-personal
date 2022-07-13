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
     * @param count {@code int}
     */
    public Node(String data, int count) {
        this.data = data;
        this.addCount(count);
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public String getData() {
        return this.data;
    }

    public int getLength() {
        return this.data.length();
    }

    public void addCount(int input) {
        this.count += input;
    }

    public void increaseCount() {
        this.addCount(1);
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode()) + ": " + this.data + " (appear "
                + this.getCount() + " times, " + this.getLength() + " characters)";
    }
}
