/**
 * Node class for linked list based data structures.
 * 
 * @author Zhang, Jin Yu
 */
public class Node<T> {
    /** Content. */
    private T data;

    /** Next linked node. */
    private Node<T> next;

    /** A counter inside the node. Only meaningful for the parent linked list. */
    private int count;

    /**
     * Construct a node with initial data.
     * 
     * @param initialData {@code T} Initial data.
     */
    public Node(T initialData) {
        this.setData(initialData);
        this.addCount(1);
    }

    /**
     * Construct a node with given data and count.
     * 
     * @param data  {@code String} A string.
     * @param count {@code int} Existing count.
     */
    public Node(T data, int count) {
        this.data = data;
        this.addCount(count);
    }

    /**
     * Add count with desired input.
     * 
     * @param input {@code int} Desired input.
     */
    public void addCount(int input) {
        this.count += input;
    }

    /**
     * Get count.
     * 
     * @return {@code int} Number of count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Set next node.
     * 
     * @param node {@code Node<T>} A node. Can not be self.
     */
    public void setNext(Node<T> node)
            throws IllegalArgumentException {
        if (node == this)
            throw new IllegalArgumentException("The next node can not be itself.");
        this.next = node;
    }

    /**
     * Set content.
     * 
     * @param input {@code T} Data.
     */
    public void setData(T input) {
        this.data = input;
    }

    /**
     * Get next node. Could return {@code null}.
     * 
     * @return {@code Node<T>} Next node.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Get content.
     * 
     * @return {@code T} Data. Could return {@code null}.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Return a string of class name, hashcode, data, count, and length.
     * 
     * @return {@code String} A string representation for the held data.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + '@' + Integer.toHexString(hashCode()) +
                " - " + this.getData() + "(count: "
                + this.getCount() + ")";
    }
}