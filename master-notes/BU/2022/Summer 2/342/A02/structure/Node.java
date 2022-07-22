package structure;

/**
 * Node class for linked list.
 * 
 * @author Zhang, Jin Yu
 */
public class Node<T> {
    /** Content. */
    private T data;

    /** Next linked node. */
    private Node<T> next;

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
     * @param node {@code Node<T>} A node. Can't assign self.
     */
    public void setNext(Node<T> node) throws IllegalArgumentException {
        if (node == this)
            throw new IllegalArgumentException("Can't assign self as next node.");
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
     * Return the string representation for the held data. This overridden method is
     * equivalent to:
     * 
     * <blockquote>
     * 
     * <pre>
     * node.getData().toString();
     * </pre>
     * 
     * </blockquote>
     * 
     * @return {@code String} A string representation for the held data.
     */
    @Override
    public String toString() {
        return this.data.toString();
    }
}
