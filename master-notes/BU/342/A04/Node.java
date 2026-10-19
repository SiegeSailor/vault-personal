/**
 * Class node for linked tree data structures.
 * 
 * @author Zhang, Jin Yu
 */
public class Node<T> {
    /** Content. */
    private T data;

    /** Counter. */
    private int count;

    /** Left child node. */
    private Node<T> left;

    /** Right child node. */
    private Node<T> right;

    /**
     * Construct a node with initial data and count.
     * 
     * @param data {@code T} Initial data.
     */
    public Node(T data) {
        this.setData(data);
        this.addCount(1);
    }

    /**
     * Set left node.
     * 
     * @param node {@code Node<T>} A node. Can not be the node self.
     */
    public void setLeft(Node<T> node)
            throws IllegalArgumentException {
        if (node == this)
            throw new IllegalArgumentException(
                    "The left node can not be the node self.");
        this.left = node;
    }

    /**
     * Get left node. Could return {@code null}.
     * 
     * @return {@code Node<T>} Left child node.
     */
    public Node<T> getLeft() {
        return this.left;
    }

    /**
     * Set right node.
     * 
     * @param node {@code Node<T>} A node. Can not be the node self.
     */
    public void setRight(Node<T> node)
            throws IllegalArgumentException {
        if (node == this)
            throw new IllegalArgumentException(
                    "The right node can not be the node self.");
        this.right = node;
    }

    /**
     * Get right node. Could return {@code null}.
     * 
     * @return {@code Node<T>} Right child node.
     */
    public Node<T> getRight() {
        return this.right;
    }

    /**
     * Set content.
     * 
     * @param input {@code T} Data. Can't be {@code null}.
     */
    public void setData(T input) throws IllegalArgumentException {
        if (input == null)
            throw new IllegalArgumentException(
                    "Can not set data as null.");
        this.data = input;
    }

    /**
     * Get content.
     * 
     * @return {@code T} Data.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Get count.
     * 
     * @return {@code int} Counter value.
     */
    public int getCount() {
        return this.count;
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
     * Return a string of class name, hashcode, data, count, and length.
     * 
     * @return {@code String} A string representation for the held data.
     */
    @Override
    public String toString() {
        return this.getData() + " (count: "
                + this.getCount() + ")";
    }
}