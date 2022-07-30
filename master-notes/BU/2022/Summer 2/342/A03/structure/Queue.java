/** Linked list version queue. */
public class Queue<T> {
    private LinkedList<T> list;

    /** Construct an queue with an empty linked list. */
    public Queue() {
        this.list = new LinkedList<T>();
    }

    /**
     * Add a data to head.
     * 
     * @param data {@code T} Data.
     */
    public void enqueue(T data) {
        this.list.addFirst(data);
    }

    /**
     * Remove a node from the end of the list.
     * 
     * @return {@code Node<T>} Current tail node.
     */
    public Node<T> dequeue() {
        return this.list.removeLast();
    }

    /**
     * Get the head node.
     * 
     * @return {@code Node<T>} The head node.
     */
    public Node<T> getHead() {
        return this.list.getHead();
    }

    /**
     * Get the tail node.
     * 
     * @return {@code Node<T>} The tail node.
     */
    public Node<T> getTail() {
        return this.list.getTail();
    }

    /**
     * See if the queue is empty.
     * 
     * @return {@code boolean} True if the queue is empty.
     */
    public boolean seeEmpty() {
        return this.list.seeEmpty();
    }

    /**
     * Get count.
     * 
     * @return {@code int} Number of nodes.
     */
    public int getCount() {
        return this.list.getCount();
    }

    /**
     * Clear the queue by removing the head node and tail node, which utilizes Java
     * Garbage Collection.
     */
    public void clear() {
        this.list.clear();
    }
}
