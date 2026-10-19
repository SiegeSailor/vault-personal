/** Node class for linked lists. */
public class Node<T> {
    /** Next node. */
    private Node<T> next;

    /** Content inside the node. */
    private T data;

    /**
     * Construct a node with given data. count will set to 1.
     * 
     * @param data {@code T} Content.
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Get the next node.
     * 
     * @return {@code Node<T>} Next node. Could be null if there is no node being
     *         pointed.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Set next node.
     * 
     * @param node {@code Node<T>} Next node.
     */
    public void setNext(Node<T> node) {
        this.next = node;
    }

    /**
     * Get the data.
     * 
     * @return {@code T} Content inside the node.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Return a string of class name, hashcode, and data.
     * 
     * @return {@code String} A string representation.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode()) +
                " (" + this.getData() + ")";
    }
}
