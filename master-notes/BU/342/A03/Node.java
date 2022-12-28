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

    /** Previous linked node. */
    private Node<T> previous;

    /**
     * Construct a node with initial data.
     * 
     * @param initialData {@code T} Initial data.
     */
    public Node(T initialData) {
        this.setData(initialData);
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
     * Get next node. Could return {@code null}.
     * 
     * @return {@code Node<T>} Next node.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Set previous node.
     * 
     * @param node {@code Node<T>} A node. Can not be self.
     */
    public void setPrevious(Node<T> node)
            throws IllegalArgumentException {
        if (node == this)
            throw new IllegalArgumentException("The previous node can not be itself.");
        this.previous = node;
    }

    /**
     * Get previous node. Could return {@code null}.
     * 
     * @return {@code Node<T>} Previous node.
     */
    public Node<T> getPrevious() {
        return this.previous;
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
        return this.getClass().getName() + " (" + this.getData() + ")";
    }
}