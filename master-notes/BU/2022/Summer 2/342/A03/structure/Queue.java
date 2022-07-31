package structure;

/**
 * Linked list version queue.
 * 
 * @author Zhang, Jin Yu
 */
public class Queue<T> {
    private LinkedList<T> list;

    /** Construct an queue with an empty linked list. */
    public Queue() {
        this.list = new LinkedList<T>();
    }

    /**
     * Construct an queue with an existing linked list.
     * 
     * @param list {@code LinkedList<T>} Existing linked list.
     */
    public Queue(LinkedList<T> list) {
        this.list = list;
    }

    /**
     * Add a data to tail.
     * 
     * @param data {@code T} Data.
     */
    public void enqueue(T data) {
        this.list.addLast(data);
    }

    /**
     * Remove a node from head.
     * 
     * @return {@code Node<T>} Current head node.
     */
    public Node<T> dequeue() {
        return this.list.removeFirst();
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

    /**
     * Clone the queue.
     * 
     * @return {@code Queue<T>} Queue that different than the original one.
     */
    @Override
    public Queue<T> clone() {
        return new Queue<T>(this.list.clone());
    }
}
